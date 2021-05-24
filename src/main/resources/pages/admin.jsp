<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Title</title>
</head>
<body>
<h1>User List</h1>
<table border="1">
    <tbody>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>LastName</td>
        <td>Email</td>
        <td>Role</td>
        <td>Password</td>
        <td>Edit</td>
        <td>Delete</td>
        <td>View</td>
    </tr>

    <tr th:if="${listUsers.empty}">
        <td colspan="2"> No Users</td>
    </tr>
    <tr th:each="user : ${listUsers}">
        <td th:text="${user.id}">ID</td>
        <td th:text="${user.name}"> Name</td>
        <td th:text="${user.lastName}"> LastName</td>
        <td th:text="${user.email}"> Email</td>
        <td>
            <p th:each="userRole: ${user.roles}">
                <span th:text="${userRole.getRole()}">role</span>
            </p>
        </td>
        <td th:text="${user.password}"> Password</td>
        <td><a th:href="@{'/edit/' + ${user.id}}">Edit</a></td>
        <td><a th:href="@{'/remove/' + ${user.id}}">Delete</a></td>
        <td><a th:href="@{'/userdata/' + ${user.id}}">View</a></td>
    </tr>
    </tbody>
</table>

<br/>
<br/>

<h2>Add user</h2>
<form action="#" th:action="@{/admin/add}" th:object="${user}" method="POST">
    <table>
        <tbody>
        <tr>
            <td><label>ID</label></td>
            <td><input size="1" type="text" path="id" readonly="true" th:field="*{id} "/></td>
        </tr>
        <tr>
            <td><label>Name</label></td>
            <td><input type="text" path="name" th:field="*{name}"/></td>
        </tr>
        <tr>
            <td><label>LastName</label></td>
            <td><input type="text" path="lastName" th:field="*{lastName}"/></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="text" path="email" th:field="*{email}"/></td>
        </tr>
        <tr>
            <td><label>Role</label></td>
            <br>
            <td>
                <select multiple size="2" name="select_role"  class="form-control">
                    <option
                            th:each="role : ${listRole}"
                            th:value="${role.id}" th:text="${role.getAuthority()}">
                    </option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label>Password</label></td>
            <td><input type="text" path="password" th:field="*{password}"/></td>
        </tr>
        <tr>
            <td th:if="${user.id == null}"><input type="submit" value="Add"></td>
            <td th:if="${user.id != null}"><input type="submit" value="Edit"></td>
        </tr>
        </tbody>
    </table>
    <br/>
    <a href="#" th:href="@{/logout}">Log Out</a>
</form>
</body>
</html>
