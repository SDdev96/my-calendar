import { EVENTS_SERVICE } from "../config/constants";

import { EventInterface } from "../config/types";

const updateEventService = async (event: EventInterface): Promise<any> => {
  return await fetch(`${EVENTS_SERVICE}/${event.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(event),
  }).then(async (response) => {
    if (!response.ok) {
      const errorData = await response.json();
      throw errorData;
    }
    return response.text();
  });
};

export default updateEventService;
