import React, { useState, useEffect } from "react";
import axios from "axios";
import { formatDistanceToNow } from "date-fns";
import { useComment } from "../hooks/useComment";

const Comments = () => {
  const user = JSON.parse(localStorage.getItem("user"));

  const { addComment } = useComment();

  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState("");

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
		<>
			{user ? (
				<div className="mt-8 w-full flex flex-col items-center ">
					<label
						htmlFor="comment"
						className="block mb-2 font-medium text-gray-800 text-2xl"
					>
						Leave a comment
					</label>
					<textarea
						id="comment"
						name="comment"
						value={comment}
						onChange={(e) => setComment(e.target.value)}
						placeholder="Enter your comment"
						rows="1"
						className="w-full px-4 py-2 border-b-2 border-black resize-none focus:outline-none focus:border-b-4"
					></textarea>
					<button
						onClick={() => {
							addComment(user, comment), setComment("");
						}}
						type="submit"
						className="p4 mt-10 p-1 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black"
					>
						Submit Comment
					</button>
				</div>
			) : (
				<br />
			)}

			<div className="w-11/12 mx-auto">
				<h2 className="text-2xl font-semibold mb-4">Comments</h2>
				<div className="space-y-4">
					{comments.map((comment, index) => (
						<div key={index} className="pb-4">
							<div className="flex space-x-2 align-bottom">
								<p className="font-semibold">
									{comment.player}
								</p>
								<p className="text-sm text-gray-500">
									{formatDistanceToNow(
										new Date(comment.commentedOn)
									)}{" "}
									ago
								</p>
							</div>
							<p>{comment.comment}</p>
						</div>
					))}
				</div>
			</div>
		</>
  );
};

export default Comments;
