import React, { useState } from 'react';
import Modal from 'react-modal';

Modal.setAppElement('#root'); 

const ProductManager = () => {
    const [modalIsOpen, setModalIsOpen] = useState(false);

    const openModal = () => {
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
    };

    // Dummy data
    const products = [
        { id: 1, name: 'Product 1' },
        { id: 2, name: 'Product 2' },
        { id: 3, name: 'Product 3' },
    ];

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
                Product
            </h1>

            <button onClick={openModal} className="border px-4 py-2 mb-4">
                Add product
            </button>

            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel="Add Product"
                className="m-4 p-4 border border-gray-300 rounded-md bg-white mt-40"
            >
                <h2 className="mb-4">Add Product</h2>
                <form>
                    
                </form>
            </Modal>

            <table className="w-full table-auto">
                <thead>
                    <tr>
                        <th className="px-4 py-2">Id</th>
                        <th className="px-4 py-2">Product Name</th>
                        <th className="px-4 py-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => (
                        <tr key={product.id}>
                            <td className="border px-4 py-2">{product.id}</td>
                            <td className="border px-4 py-2">{product.name}</td>
                            <td className="border px-4 py-2">
                                <button className="border px-2 py-1 mr-2">Edit</button>
                                <button className="border px-2 py-1">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ProductManager;