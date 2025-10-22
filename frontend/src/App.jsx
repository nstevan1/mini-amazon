import React, { useState, useEffect } from "react";
import axios from "axios";

export default function App() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({
    title: "",
    description: "",
    price: "",
    inventory: "",
  });

  // Load all products on component mount
  useEffect(() => {
    axios
      .get("/catalog")
      .then((res) => setProducts(res.data))
      .catch((err) => console.error("Error fetching products:", err));
  }, []);

  // Handle form field changes
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Add a new product
  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("/catalog", {
        title: form.title,
        description: form.description,
        price: parseFloat(form.price),
        inventory: parseInt(form.inventory),
      })
      .then((res) => {
        setProducts([...products, res.data]);
        setForm({ title: "", description: "", price: "", inventory: "" });
      })
      .catch((err) => console.error("Error adding product:", err));
  };

  // Delete a product
  const handleDelete = (id) => {
    axios
      .delete(`/catalog/${id}`)
      .then(() => setProducts(products.filter((p) => p.id !== id)))
      .catch((err) => console.error("Error deleting product:", err));
  };

  return (
    <div style={{ fontFamily: "Arial, sans-serif", margin: "2rem" }}>
      <h1>🛒 Mini Amazon</h1>

      {/* Add product form */}
      <form
        onSubmit={handleSubmit}
        style={{
          marginBottom: "2rem",
          display: "flex",
          flexDirection: "column",
          gap: "0.5rem",
          maxWidth: "400px",
        }}
      >
        <input
          name="title"
          placeholder="Title"
          value={form.title}
          onChange={handleChange}
          required
        />
        <textarea
          name="description"
          placeholder="Description"
          value={form.description}
          onChange={handleChange}
          rows={2}
        />
        <input
          name="price"
          type="number"
          step="0.01"
          placeholder="Price"
          value={form.price}
          onChange={handleChange}
          required
        />
        <input
          name="inventory"
          type="number"
          placeholder="Inventory"
          value={form.inventory}
          onChange={handleChange}
          required
        />
        <button type="submit">Add Product</button>
      </form>

      {/* Product list */}
      <h2>📦 Product List</h2>
      {products.length === 0 ? (
        <p>No products found.</p>
      ) : (
        <ul>
          {products.map((p) => (
            <li key={p.id} style={{ marginBottom: "1rem" }}>
              <strong>{p.title}</strong> — ${p.price.toFixed(2)} <br />
              <small>{p.description}</small> <br />
              <small>Inventory: {p.inventory}</small> <br />
              <button
                style={{
                  marginTop: "0.5rem",
                  background: "tomato",
                  color: "white",
                  border: "none",
                  padding: "0.3rem 0.6rem",
                  cursor: "pointer",
                }}
                onClick={() => handleDelete(p.id)}
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
