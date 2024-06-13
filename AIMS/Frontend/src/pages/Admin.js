import React, { useEffect, useState, useContext } from "react";
import Modal from "react-modal";
import { UserContext } from "../providers/UserContext";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import axios from "axios";
Modal.setAppElement("#root");
const Admin = () => {
  const navigate = useNavigate();
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const { isAuthen } = useContext(UserContext);
  const [users, setUsers] = useState([]);
  const [selectedRole, setSelectedRole] = useState("");
  const [username, setUsername] = useState([]);
  const [password, setPassword] = useState([]);

  const fetchUsers = () => {
    axios
      .get("user/all")
      .then((response) => {
        setUsers(response.data.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  useEffect(() => {
    if (!isAuthen) {
      toast.error("You are not authorized to view this page");
      navigate("/");
    }
  }, [isAuthen, navigate]);

  const openModal = () => {
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setModalIsOpen(false);
  };

  const handleRoleChange = (e) => {
    setSelectedRole(e.target.value);
  };

  const handelResetPassword = (user) => {
    const newUser = {
      ...user,
      password: "123456",
    };

    axios
      .put(`user/update/${user.id}`, newUser)
      .then((response) => {
        fetchUsers();
        toast.success("Password reset successfully");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleBlockStatus = (user) => {
    const newUser = {
      ...user,
      blockStatus: !user.blockStatus,
    };
    axios
      .put(`user/update/${user.id}`, newUser)
      .then((response) => {
        fetchUsers();
        toast.success("Block status updated successfully");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleDeleteUser = (id) => {
    axios
      .delete(`user/delete?userId=${id}`)
      .then((response) => {
        fetchUsers();
        toast.success("User deleted successfully");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleCreateNewUser = (e) => {
    e.preventDefault();
    axios
      .post(
        `user/create?username=${username}&password=${password}&role=${selectedRole}`
      )
      .then((response) => {
        setModalIsOpen(false);
        fetchUsers();
        toast.success("User created successfully");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const changeUserRole = (role, username, password, id) => {
    axios
      .put(`user/update/${id}`, {
        username: username,
        password: password,
        role: role,
      })
      .then((response) => {
        fetchUsers();
        toast.success("Role updated successfully");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl mb-4">User</h1>

      <button onClick={openModal} className="border px-4 py-2 mb-4 font-bold">
        Add user
      </button>

      <Modal
        isOpen={modalIsOpen}
        onRequestClose={closeModal}
        contentLabel="Add User"
        className="m-4 p-4 border-2  border-gray-300 rounded-md bg-gray-50 mt-40"
      >
        <h2 className="mb-4 font-bold">Add User</h2>
        <form onSubmit={handleCreateNewUser}>
          <label className="block mb-2">
            Username
            <input
              type="text"
              className="border px-2 py-1 w-full"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </label>
          <label className="block mb-2">
            Password
            <input
              type="password"
              className="border px-2 py-1 w-full"
              username={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </label>
          <label className="block mb-4">
            Role
            <select
              value={selectedRole}
              onChange={handleRoleChange}
              className="border px-2 py-1 w-full"
            >
              <option value="">Select Role</option>
              <option value="admin">Admin</option>
              <option value="product_manager">Product Manager</option>
            </select>
          </label>
          <button
            type="button"
            onClick={closeModal}
            className="border px-4 py-2 mr-2"
          >
            Cancel
          </button>
          <button type="submit" className="border px-4 py-2">
            Save
          </button>
        </form>
      </Modal>

      <table className="w-full table-auto bordernpm install react-modal">
        <thead>
          <tr>
            <th className="px-4 py-2">Id</th>
            <th className="px-4 py-2">UserName</th>
            <th className="px-4 py-2">Password</th>
            <th className="px-4 py-2">Role</th>
            <th className="px-4 py-2">Block Status</th>
            <th className="px-4 py-2">Action</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => {
            return (
              <tr key={user.id}>
                <td className="border px-4 py-2">{user.id}</td>
                <td className="border px-4 py-2">{user.username}</td>
                <td className="border px-4 py-2">{user.password}</td>
                <td className="border px-4 py-2">
                  <label className="block mb-4">
                    <select
                      value={user.role}
                      onChange={(e) =>
                        changeUserRole(
                          e.target.value,
                          user.username,
                          user.password,
                          user.id
                        )
                      }
                      className="border px-2 py-1 w-full"
                    >
                      <option value="admin">Admin</option>
                      <option value="product_manager">Product Manager</option>
                    </select>
                  </label>
                </td>

                <td className="border px-4 py-2">
                  {user.blockStatus ? "Blocked" : "Active"}
                </td>

                <td className="border px-4 py-2 flex justify-around">
                  <button
                    className="border rounded-2xl px-2 py-1"
                    onClick={() => handelResetPassword(user)}
                  >
                    Reset Password
                  </button>
                  <button
                    className="border rounded-2xl px-2 py-1"
                    onClick={() => handleBlockStatus(user)}
                  >
                    {user.blockStatus ? "Unblock" : "Block"}
                  </button>
                  <button
                    className="border rounded-2xl px-2 py-1"
                    onClick={() => handleDeleteUser(user.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default Admin;
