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

  // Load products from backend
  useEffect(() => {
    axios
      .get("/catalog")
      .then((res) => setProducts(res.data))
      .catch((err) => console.error("Error fetching products:", err));
  }, []);

  // Handle input change
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Handle form submit
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

  return (
    <div style={{ fontFamily: "Arial, sans-serif", margin: "2rem" }}>
      <h1>🛒 Mini Amazon</h1>

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
          placeholder="Price"
          type="number"
          step="0.01"
          value={form.price}
          onChange={handleChange}
          required
        />
        <input
          name="inventory"
          placeholder="Inventory"
          type="number"
          value={form.inventory}
          onChange={handleChange}
          required
        />
        <button type="submit">Add Product</button>
      </form>

      <h2>📦 Product List</h2>
      {products.length === 0 ? (
        <p>No products found.</p>
      ) : (
        <ul>
          {products.map((p) => (
            <li key={p.id}>
              <strong>{p.title}</strong> — ${p.price.toFixed(2)} <br />
              <small>{p.description}</small> <br />
              <small>Inventory: {p.inventory}</small>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
