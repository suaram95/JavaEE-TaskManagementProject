package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private Connection connection;

    private UserManager userManager=new UserManager();

    public TaskManager(){
        connection= DBConnectionProvider.getInstance().getConnection();
    }

    public void addTask(Task task){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT into task(name,description,status,deadline,user_id) " +
                    "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus().name());
            statement.setString(4, DateUtil.getStringFromDate(task.getDeadline()));
            statement.setInt(5, task.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                task.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks(){
        List<Task> taskList=new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");

            while (resultSet.next()){
                taskList.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }


    public Task getTaskById(int taskId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM task WHERE id=?");
            statement.setInt(1, taskId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return getTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTasksByUser(int currentUserId) {
        List<Task> taskList=new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM task WHERE user_id=?");
            statement.setInt(1, currentUserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                taskList.add(getTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void changeTaskStatus(int taskId, String taskStatus) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE task Set status=? WHERE id=?");
            statement.setString(1,taskStatus);
            statement.setInt(2,taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeTaskUser(int taskId, int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE task SET user_id=? WHERE id=?");
            statement.setInt(1, userId);
            statement.setInt(2, taskId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Task getTaskFromResultSet(ResultSet resultSet) throws SQLException {
        return Task.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .description(resultSet.getString(3))
                .status(TaskStatus.valueOf(resultSet.getString(4)))
                .deadline(DateUtil.getDateFromString(resultSet.getString(5)))
                .user(userManager.getUserById(resultSet.getInt(6)))
                .build();
    }



}

