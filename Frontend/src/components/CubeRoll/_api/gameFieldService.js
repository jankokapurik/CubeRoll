import axios from "axios";

const FIELD_URL = '/api/cuberoll/field';
const NEW_GAME_URL = `${FIELD_URL}/newGame`;
const MOVE_RIGHT_URL = `${FIELD_URL}/right`;
const MOVE_LEFT_URL = `${FIELD_URL}/left`;
const MOVE_UP_URL = `${FIELD_URL}/up`;
const MOVE_DOWN_URL = `${FIELD_URL}/down`;

export const fetchField = async (level) => {
    try {
        const response = await axios.get(`${NEW_GAME_URL}/${level}`);
        // console.log(response.data)
        return response.data;
    } catch (error) {
        console.error("Error fetching field:", error);
        throw error;
    }
};

export const moveCubeRight = async () => {
    try {
        const response = await axios.get(MOVE_RIGHT_URL);
        return response.data;
    } catch (error) {
        console.error("Error moving cube right:", error);
        throw error;
    }
};
export const moveCubeLeft = async () => {
    try {
        const response = await axios.get(MOVE_LEFT_URL);
        return response.data;
    } catch (error) {
        console.error("Error moving cube left:", error);
        throw error;
    }
};
export const moveCubeUp = async () => {
    try {
        const response = await axios.get(MOVE_UP_URL);
        return response.data;
    } catch (error) {
        console.error("Error moving cube up:", error);
        throw error;
    }
};
export const moveCubeDown = async () => {
    try {
        const response = await axios.get(MOVE_DOWN_URL);
        return response.data;
    } catch (error) {
        console.error("Error moving cube down:", error);
        throw error;
    }
};
