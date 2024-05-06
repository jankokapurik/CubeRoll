import axios from 'axios';

const FIELD_URL = 'api/cuberoll/field';
const NEW_GAME_URL = `${FIELD_URL}/newGame/`;

export const fetchField = async (level) => {
    try {
        const response = await axios.get(`${NEW_GAME_URL}?level=${level}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching field:", error);
        throw error; // Rethrow the error to handle it in the calling component
    }
};
