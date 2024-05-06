// CubeRoll.jsx
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { fetchField, moveCubeRight, moveCubeLeft, moveCubeUp, moveCubeDown } from "./_api/gameFieldService"; // Import the fetchField function

const CubeRoll = () => {
	let { level } = useParams();
	const [data, setData] = useState(null);

	useEffect(() => {
		const handleFetchData = async () => {
			try {
				const data = await fetchField(level);
				setData(data);
				console.log(data);
			} catch (error) {
				console.error("Error fetching field:", error);
			}
		};
		handleFetchData();
	}, [level]);

	 const handleKeyDown = async (event) => {
			try {
				if (event.key === "ArrowRight") {
					const newData = await moveCubeRight();
					setData(newData);
				} else if (event.key === "ArrowLeft") {
					const newData = await moveCubeLeft();
					setData(newData);
				} else if (event.key === "ArrowUp") {
					const newData = await moveCubeUp();
					setData(newData);
				} else if (event.key === "ArrowDown") {
					const newData = await moveCubeDown();
					setData(newData);
				}
				 if (newData.state === "SOLVED") {
                // Perform action when game is solved
                console.log("Congratulations! You solved the game!")	}
			} catch (error) {
				console.error("Error moving cube:", error);
			}
		};

	useEffect(() => {
		window.addEventListener("keydown", handleKeyDown);
		return () => {
			window.removeEventListener("keydown", handleKeyDown);
		};
	}, []); 


	return (
		<div className="cube-roll">
			<h2>Game for Level {level}</h2>
			<div className="flex flex-col">
				{data &&
					data.tiles.map((row, rowIndex) => (
						<div className="flex flex-row" key={rowIndex}>
							{row.map((element, colIndex) => (
								<div
									key={`${rowIndex}-${colIndex}`}
									className="relative"
								>
									<div
										className={
											element.type === "null"
												? "m-1 h-16 w-16 opacity-100"
												: element.type === "finish"
												? "m-1 h-16 w-16 bg-red-500"
												: element.type === "button"
												? "m-1 h-16 w-16 bg-blue-500"
												: element.type == "paint"
												? `m-1 h-16 w-16 flex justify-center align-middle text-white text-3xl`
												: "m-1 h-16 w-16 bg-gray-600"
										}
										style={
											element.color
												? {
														backgroundColor:
															element.color,
															
												  }
												: null
										}
									>
										{rowIndex === data.cubeYPos &&
										colIndex === data.cubeXPos ? (
											<div
												className="w-full h-full flex flex-col allign-middle justify-center"
												style={{ zIndex: 1 }}
											>
												<div
													className="h-3 mx-auto w-10 border border-gray-700"
													style={{
														backgroundColor:
															data.cube.up,
													}}
												></div>
												<div className="flex flex-row h-10 w-full">
													<div
														className="w-3 h-10 border border-gray-700"
														style={{
															backgroundColor:
																data.cube.left,
														}}
													></div>
													<div
														className="w-10 h-10 "
														style={{
															backgroundColor:
																data.cube.right,
														}}
													></div>
													<div
														className="w-3 h-10 border border-gray-700"
														style={{
															backgroundColor:
																data.cube.right,
														}}
													></div>
												</div>

												<div
													className="h-3 w-10  mx-auto border border-gray-700"
													style={{
														backgroundColor:
															data.cube.down,
													}}
												></div>
											</div>
										) : element.type == "paint" ? (
											"+"
										) : null}
									</div>
								</div>
							))}
						</div>
					))}
			</div>
		</div>
	);

};

export default CubeRoll;
