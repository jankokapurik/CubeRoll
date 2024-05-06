import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const useAuth = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const login = async (username, password) => {
    setIsLoading(true);

    try {
      const response = await axios.post(
        "http://localhost:5001/api/v1/user/login",
        { username, password }
      );

      if (response.status === 200) {
        console.log(response);
        localStorage.setItem("user", JSON.stringify(response.data));
        navigate("/");
      }
    } catch (error) {
      console.error("Login failed:", error.response.data);
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  const register = async (username, password) => {
    setIsLoading(true);

    try {
      const response = await axios.post(
        "http://localhost:5001/api/v1/user/register",
        { username, password }
      );

      if (response.status === 200) {
        localStorage.setItem("user", JSON.stringify(response.data));
        navigate("/");
      }
    } catch (error) {
      console.error("Register failed:", error.response.data);
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  return { login, register, logout, error, isLoading };
};
