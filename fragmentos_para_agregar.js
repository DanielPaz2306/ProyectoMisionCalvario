// ============================================================
// FRAGMENTOS PARA AGREGAR EN apiService.js
// ============================================================
// Agregar estas funciones al final de tu apiService.js existente
// (después de las funciones de Distritos)

// ---------- IGLESIAS ----------
export const getIglesias = () =>
  api.get('/iglesias').then((r) => r.data);

export const getIglesiasByDistrito = (distritoId) =>
  api.get(`/iglesias/distrito/${distritoId}`).then((r) => r.data);

export const getIglesiaById = (id) =>
  api.get(`/iglesias/${id}`).then((r) => r.data);

export const createIglesia = (data) =>
  api.post('/iglesias', data).then((r) => r.data);

export const updateIglesia = (id, data) =>
  api.put(`/iglesias/${id}`, data).then((r) => r.data);

export const deleteIglesia = (id) =>
  api.delete(`/iglesias/${id}`).then((r) => r.data);

// ---------- PASTORES (funciones adicionales) ----------
// Si aún no las tienes en tu apiService.js:

export const getPastores = () =>
  api.get('/pastores').then((r) => r.data);

export const getPastoresSinIglesia = () =>
  api.get('/pastores/sin-iglesia').then((r) => r.data);

export const getPastoresByDistrito = (distritoId) =>
  api.get(`/pastores/distrito/${distritoId}`).then((r) => r.data);


// ============================================================
// FRAGMENTOS PARA App.js
// ============================================================
// 1. Agregar el import arriba:
import Iglesias from './pages/Iglesias';

// 2. Agregar la ruta dentro de <Routes> (junto a /distritos):
<Route
  path="/iglesias"
  element={
    <RutaProtegida rolesPermitidos={['ADMIN', 'AP', 'PD', 'PASTOR']}>
      <Iglesias />
    </RutaProtegida>
  }
/>


// ============================================================
// FRAGMENTO PARA Dashboard.js — agregar enlace en el sidebar
// ============================================================
// Busca donde tienes el enlace de /distritos en el sidebar y agrega debajo:

// Ejemplo de cómo debe quedar (ajusta tu estructura actual):
// { label: 'Iglesias', path: '/iglesias', roles: ['ADMIN', 'AP', 'PD', 'PASTOR'] }

// Si tu sidebar es un array de objetos, agrega:
{ label: 'Iglesias', path: '/iglesias', roles: ['ADMIN', 'AP', 'PD', 'PASTOR'] }

// Si tu sidebar renderiza Links directamente, agrega algo como:
// {(rol === 'ADMIN' || rol === 'AP' || rol === 'PD' || rol === 'PASTOR') && (
//   <Link to="/iglesias">Iglesias</Link>
// )}
