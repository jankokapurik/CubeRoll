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
									{rowIndex === data.cubeYPos &&
										colIndex === data.cubeXPos && (
											<div
												className="absolute w-full h-full"
												style={{ zIndex: 1 }}
											>
												{/* Render your cube here */}
												<svg
													xmlns="http://www.w3.org/2000/svg"
													width="100%"
													height="100%"
													viewBox="0 0 24 24"
													fill="yellow"
												>
													<path
														d="M0 0h24v24H0z"
														fill="none"
													/>
													<path d="M20 12c0-4.41-3.59-8-8-8s-8 3.59-8 8c0 3.74 2.56 6.89 6 7.75V22h4v-2.25c3.44-.86 6-4.01 6-7.75zm-6 5.75V20h-4v-2.25c-2.88-.71-5-3.36-5-6.5 0-3.04 2.46-5.5 5.5-5.5s5.5 2.46 5.5 5.5c0 3.14-2.12 5.79-5 6.5zm-2-8.75c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5z" />
												</svg>
											</div>
										)}
									<div
										className={
											element.type === "null"
												? "m-1 h-10 w-10 opacity-100"
												: element.type === "finish"
												? "m-1 h-10 w-10 bg-red-500"
												: element.type === "button"
												? "m-1 h-10 w-10 bg-blue-500"
												: element.type == "paint"
												? `m-1 h-10 w-10 flex justify-center align-middle text-white text-3xl`
												: "m-1 h-10 w-10 bg-gray-600"
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
										{element.type == "paint" ? "+" : ""}
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
