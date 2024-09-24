import gsAxios from "../../../../_api"

const FIELD_URL = 'cuberoll/field';
const NER_GAME_URL = FIELD_URL + "/newGame";


const fetchField = () => gsAxios.get