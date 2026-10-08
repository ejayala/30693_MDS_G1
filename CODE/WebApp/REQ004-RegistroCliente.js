import React, { useState } from 'react';
export default function RegistroCliente() {
  const [form, setForm] = useState({
    cedula: '',
    nombre: '',
    apellido: '',
    telefono: '',
    direccion: '',
    fechaCumpleanos: '',
    estado: 'Activo'
  });
  const [errores, setErrores] = useState({});
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    let err = {};
    if (!form.cedula || form.cedula.length !== 10) {
      err.cedula = 'La cédula debe tener exactamente 10 dígitos numéricos';
    } else {
      let suma = 0;
      for (let i = 0; i < 9; i++) {
        let dig = parseInt(form.cedula[i]) * (i % 2 === 0 ? 2 : 1);
        suma += dig > 9 ? dig - 9 : dig;
      }
      let verif = (10 - (suma % 10)) % 10;
      if (verif !== parseInt(form.cedula[9])) {
        err.cedula = 'Cédula inválida según dígito verificador';
      }
    }
    if (!form.nombre.trim()) err.nombre = 'El nombre es obligatorio';
    if (!form.apellido.trim()) err.apellido = 'El apellido es obligatorio';
    if (!form.telefono || !/^\d{10}$/.test(form.telefono)) err.telefono = 'Teléfono inválido (10 dígitos)';
    if (!form.fechaCumpleanos) err.fechaCumpleanos = 'Fecha obligatoria';
    if (Object.keys(err).length > 0) {
      setErrores(err);
      return;
    }
    try {
      const resp = await fetch('/api/clientes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form)
      });
      if (resp.ok) alert('Cliente registrado con éxito');
    } catch (error) {
      alert('Error de conexión al guardar cliente');
    }
  };
  return (
    <form onSubmit={handleSubmit} className="p-4 border rounded">
      <h3>Registro de Cliente - Los Huertos del JuanK</h3>
      <input name="cedula" placeholder="Cédula" onChange={handleChange} />
      {errores.cedula && <span>{errores.cedula}</span>}
      <input name="nombre" placeholder="Nombre" onChange={handleChange} />
      {errores.nombre && <span>{errores.nombre}</span>}
      <input name="apellido" placeholder="Apellido" onChange={handleChange} />
      {errores.apellido && <span>{errores.apellido}</span>}
      <input name="telefono" placeholder="Teléfono" onChange={handleChange} />
      {errores.telefono && <span>{errores.telefono}</span>}
      <input name="direccion" placeholder="Dirección" onChange={handleChange} />
      <input type="date" name="fechaCumpleanos" onChange={handleChange} />
      {errores.fechaCumpleanos && <span>{errores.fechaCumpleanos}</span>}
      <button type="submit">Guardar Cliente</button>
    </form>
  );
}
