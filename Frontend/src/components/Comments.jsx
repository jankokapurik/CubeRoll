import React, { useState, useEffect } from "react";
import axios from "axios";
import { formatDistanceToNow } from "date-fns";

const Comments = () => {
	const [comments, setComments] = useState([]);

	useEffect(() => {
		const fetchComments = async () => {
			try {
				const response = await axios.get("/api/comment/cuberoll");
				setComments(response.data);
			} catch (error) {
				console.error("Error fetching scores:", error);
			}
		};

		fetchComments();
	}, []);

	return (
		<div className="w-11/12 mx-auto">
			<h2 className="text-2xl font-semibold mb-4">Comments</h2>
			<div className="space-y-4">
				{comments.map((comment, index) => (
					<div key={index} className="pb-4">
                        <div className="flex space-x-2 align-bottom">
                            <p className="font-semibold">{comment.player}</p>
                            <p className="text-sm text-gray-500">
                                {formatDistanceToNow(new Date(comment.commentedOn))}{" "}
                                ago
                            </p>
                        </div>
						<p>{comment.comment}</p>
					</div>
				))}
			</div>
		</div>
	);
};

export default Comments;
