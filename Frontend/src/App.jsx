import Header from './components/layout/Header'
import Footer from './components/layout/Footer'
import Index from './components/Index'
import HallOfFame from './components/HallOfFame'
import Comments from './components/Comments'
import CubeRoll from './components/CubeRoll/CubeRoll'
import LevelSelector from './components/CubeRoll/LevelSelector'
import { Routes, Route } from 'react-router-dom'
import './index.css'

function App() {
	

	return (
		<div className="min-h-screen flex flex-col justify-between font-Cube_Font">
			<Header />
			<body className="w-full flex justify-center align-middle">
				<Routes>
					<Route path="/" element={<Index />} />
					<Route
						path="/cuberoll/new"
						element={
							<LevelSelector/>
						}
					/>
					<Route
						path="/cuberoll/:level"
						element={<CubeRoll/>}
					/>
					<Route path="/scores" element={<HallOfFame />} />
					<Route path="/comments" element={<Comments />} />
				</Routes>
			</body>
			<Footer />
		</div>
	);
}

export default App
