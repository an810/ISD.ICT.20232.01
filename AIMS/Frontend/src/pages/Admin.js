import React, {useState} from 'react';
import Modal from 'react-modal';

Modal.setAppElement('#root'); 
const Admin = () => {
    const [modalIsOpen, setModalIsOpen] = useState(false);

    const openModal = () => {
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
    };

    return (
        <div className="p-6">
            <div className="flex justify-end mb-4">
                <button className="border px-4 py-2 mr-2">
                    Change password
                </button>

                <button className="border px-4 py-2">
                    Log out
                </button>
            </div>

            <h1 className="text-2xl mb-4">
                User
            </h1>

            <button onClick={openModal} className="border px-4 py-2 mb-4">
                Add user
            </button>

            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel="Add User"
                className="m-4 p-4 border border-gray-300 rounded-md bg-white mt-40"
            >
                <h2 className="mb-4">Add User</h2>
                <form>
                    <label className="block mb-2">
                        Username
                        <input type="text" className="border px-2 py-1 w-full" />
                    </label>
                    <label className="block mb-2">
                        Password
                        <input type="password" className="border px-2 py-1 w-full" />
                    </label>
                    <label className="block mb-4">
                        Role
                        <input type="text" className="border px-2 py-1 w-full" />
                    </label>
                    <button type="button" onClick={closeModal} className="border px-4 py-2 mr-2">
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
                        <th className="px-4 py-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td className="border px-4 py-2">1</td>
                        <td className="border px-4 py-2">User1</td>
                        <td className="border px-4 py-2 flex justify-around">
                            <button className="border px-2 py-1">Select Role</button>
                            <button className="border px-2 py-1">Block</button>
                            <button className="border px-2 py-1">Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
}

export default Admin;