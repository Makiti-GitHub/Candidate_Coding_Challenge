import { useState } from "react";
import Course from "../dtos/Course";

interface NewCourceProps {
  onCreate: (course: Omit<Course, "id">) => void;
  creationPending: boolean;
}

const NewCource: React.FC<NewCourceProps> = ({ onCreate, creationPending }) => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");
  return (
    <div>
      <div>
        <label>Name</label>
        <input onChange={(e) => setName(e.target.value)} />
      </div>
      <div>
        <label>Price</label>
        <input
          onChange={(e) => setPrice(parseInt(e.target.value))}
          type="number"
        />
      </div>
      <div>
        <label>Description</label>
        <input onChange={(e) => setDescription(e.target.value)} />
      </div>
      <div>
        <button
          onClick={() => onCreate({ name, price, description })}
          disabled={creationPending}
        >
          Save
        </button>
        {creationPending && <label>Saving ....</label>}
      </div>
    </div>
  );
};

export default NewCource;
