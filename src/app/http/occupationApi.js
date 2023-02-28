import {$host} from "./clientsApi";

export const fetchMaleOccupations = async () => {
     const {data} = await $host.get("api/occupations/male");
     return data;
}
export const fetchFemaleOccupations = async () => {
     const {data} = await $host.get("api/occupations/female");
     return data;
}