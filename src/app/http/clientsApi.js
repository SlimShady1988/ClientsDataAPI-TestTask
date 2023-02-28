import axios from "axios";
const $host = axios.create({
     baseURL: process.env.REACT_APP_API_URL,
});

export const createClient = async (client) => {
     const {data} = await $host.post("api/clients/create", client);
     return data;
}

export const fetchClients = async (page) => {
     const {data} = await $host.get("api/clients/list", {params: {page}});
     return data;
}

export const getClient = async (id) => {
     const {data} = await $host.get("api/clients/" + id);
     return data;
}
export const editClient = async (id, client) => {
     const {data} = await $host.put("api/clients/" + id +"/edit", client);
     return data;
}

export const removeClient = async (id) => {
     const {data} = await $host.delete("api/clients/delete/" + id);
     return data;
}
export {$host};


