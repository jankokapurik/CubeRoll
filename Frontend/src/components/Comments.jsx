import React, { useState, useEffect } from "react";
import axios from "axios";
import { formatDistanceToNow } from "date-fns";

const Comments = () => {
  const user = localStorage.getItem("user");

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

  const handleCommentChange = (event) => {
    setComment(event.target.value);
  };

  const handleSubmitComment = async (event) => {
    event.preventDefault();
    try {
      await axios.post("/api/comment", { content: comment });
      setComment("");
    } catch (error) {
      console.error("Error submitting comment:", error);
    }
  };

  return (
    <>
      {user ? (
        <form onSubmit={handleSubmitComment} className="mt-8">
          <label
            htmlFor="comment"
            className="block mb-2 font-medium text-gray-800"
          >
            Add a Comment:
          </label>
          <textarea
            id="comment"
            name="comment"
            value={comment}
            onChange={handleCommentChange}
            rows="4"
            className="w-full px-4 py-2 border rounded-md resize-none focus:outline-none focus:ring-2 focus:ring-blue-500"
          ></textarea>
          <button
            type="submit"
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            Submit Comment
          </button>
        </form>
      ) : (
        <br />
      )}

      <div className="w-11/12 mx-auto">
        <h2 className="text-2xl font-semibold mb-4">Comments</h2>
        <div className="space-y-4">
          {comments.map((comment, index) => (
            <div key={index} className="pb-4">
              <div className="flex space-x-2 align-bottom">
                <p className="font-semibold">{comment.player}</p>
                <p className="text-sm text-gray-500">
                  {formatDistanceToNow(new Date(comment.commentedOn))} ago
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
