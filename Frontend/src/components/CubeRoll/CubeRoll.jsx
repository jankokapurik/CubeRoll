import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { fetchField, moveCubeRight, moveCubeLeft, moveCubeUp, moveCubeDown } from "./_api/gameFieldService";
import { useGame } from "../../hooks/useGame";
import teleportImage from "../../assets/teleport.png";
import finishImage from "../../assets/finish.png";
import buttonImage from "../../assets/button.png";
import plusSvg from "../../assets/plus.svg";
import { useNavigate } from "react-router-dom";



const CubeRoll = () => {
    const { saveGame } = useGame();
    const user = JSON.parse(localStorage.getItem("user"));
    let { level } = useParams();
    const [data, setData] = useState(null);
	const [solved, setSolved] = useState(false);
	const navigate = useNavigate();

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

	useEffect(() => {
		setData(null);
		setSolved(false); // Reset data state when level changes
	}, [level]);

    const handleKeyDown = async (event) => {
        try {
            let newData = data;
            if (event.key === "ArrowRight") {
                newData = await moveCubeRight();
                setData(newData);
            } else if (event.key === "ArrowLeft") {
                newData = await moveCubeLeft();
                setData(newData);
            } else if (event.key === "ArrowUp") {
                newData = await moveCubeUp();
                setData(newData);
            } else if (event.key === "ArrowDown") {
                newData = await moveCubeDown();
                setData(newData);
            }
            console.log(newData.state);
            if (newData.state === "SOLVED") {
				setSolved(true);
                removeEventListener("keydown", handleKeyDown);
                saveGame(user, 100 * newData.min_moves / newData.moves);
            }
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

	const handleNextRound = () => {
		console.log("Moving to the next round...");
		navigate(`/cuberoll/${parseInt(level) + 1}`);
	};

    return (
		<div className="flex flex-col items-center mt-20">
			<h2 className="text-3xl mb-4">Level {level}</h2>
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
												? "m-1 h-16 w-16 opacity-100 rounded-md"
												: element.type === "finish"
												? "m-1 h-16 w-16 bg-red-500 rounded-md  shadow-md shadow-gray-700"
												: element.type === "paint"
												? `m-1 h-16 w-16 flex items-center justify-center shadow-md shadow-gray-700 text-white text-3xl rounded-md`
												: "m-1 h-16 w-16 bg-gray-600 rounded-md shadow-md shadow-gray-700"
										}
										style={
											element.color
												? {
														backgroundColor:
															element.color,
												  }
												: element.type === "teleport"
												? {
														backgroundImage: `url(${teleportImage})`,
														backgroundSize: "cover",
												  }
												: element.type === "finish"
												? {
														backgroundImage: `url(${finishImage})`,
														backgroundSize: "cover",
												  }
												: element.type === "button"
												? {
														backgroundImage: `url(${buttonImage})`,
														backgroundSize: "cover",
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
													className="h-2 mx-auto w-12 shadow-sm shadow-gray-700"
													style={{
														backgroundColor:
															data.cube.up,
													}}
												></div>
												<div className="flex flex-row h-12 w-full shadow-inner shadow-gray-700">
													<div
														className="w-2 h-12"
														style={{
															backgroundColor:
																data.cube.left,
														}}
													></div>
													<div
														className="w-12 h-12 shadow-xl shadow-black z-10"
														style={{
															backgroundColor:
																data.cube.top,
														}}
													></div>
													<div
														className="w-2 h-12"
														style={{
															backgroundColor:
																data.cube.right,
														}}
													></div>
												</div>

												<div
													className="h-2 w-12  mx-auto "
													style={{
														backgroundColor:
															data.cube.down,
													}}
												></div>
											</div>
										) : element.type === "paint" ? (
											<img
												src={plusSvg}
												alt="Paint"
												className="h-10 w-10 invert"
											/>
										) : // ) : element.type === "button" ? (
										// 	<div className="absolute top-0 left-0 w-full h-full flex items-center justify-center">
										// 		<div className="h-12 w-12 bg-red-600 rounded-full shadow-md shadow-black"></div>
										// 	</div>
										null}
									</div>
								</div>
							))}
						</div>
					))}
			</div>
			<div className="text-xl mt-4 w-full">
				{data && `${data.moves} / ${data.min_moves}`}
			</div>
			{solved && (
				<button
					onClick={handleNextRound}
					className="p4 px-4 p-1 mt-2 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black"
				>
					Next Round
				</button>
			)}
		</div>
	);
};

export default CubeRoll;
