import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Index from "./components/Index";
import HallOfFame from "./components/HallOfFame";
import Comments from "./components/Comments";
import CubeRoll from "./components/CubeRoll/CubeRoll";
import LevelSelector from "./components/CubeRoll/LevelSelector";
import { Routes, Route } from "react-router-dom";
import "./index.css";
import Login from "./pages/Login";
import Register from "./pages/Register";
import PrivateRoute from "./pages/PrivateRoute";

function App() {
  return (
    <div className="min-h-screen flex flex-col justify-between font-Cube_Font">
      <Header />
      <body className=" flex justify-center align-middle mt-28">
        <Routes>
          <Route path="/" element={<Index />} />
          <Route path="/scores" element={<HallOfFame />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          <Route element={<PrivateRoute />}>
            <Route path="/cuberoll/new" element={<LevelSelector />} />
            <Route path="/cuberoll/:level" element={<CubeRoll />} />
            <Route path="/comments" element={<Comments />} />
          </Route>
        </Routes>
      </body>
      <Footer />
    </div>
  );
}

export default App;
