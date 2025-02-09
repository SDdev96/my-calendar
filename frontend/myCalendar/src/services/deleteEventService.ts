import { EVENTS_SERVICE } from "../config/constants";

const deleteEventService = async (id: number): Promise<void> => {
  return fetch(`${EVENTS_SERVICE}/${id}`, {
    method: "DELETE",
  }).then(async (response) => {
    if (!response.ok) {
      const errorData = await response.json();
      throw errorData;
    }
  });
};

export default deleteEventService;
