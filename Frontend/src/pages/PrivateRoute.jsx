import React, { useEffect } from "react";
import { useNavigate, Outlet } from "react-router-dom";

export default function PrivateRoute() {
  const user = localStorage.getItem("user");
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate("/");
    }
  }, [user, navigate]);

  return user ? <Outlet /> : null;
}
