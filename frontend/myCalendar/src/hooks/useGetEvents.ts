import { useEffect, useState } from "react";
import eventsService from "../services/eventsService";
import { EventInterface, EventParams } from "../config/types";

const useGetEvents = ({ title }: EventParams = {}, refreshTrigger: boolean) => {
  const [data, setData] = useState<EventInterface[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [isPending, setIsPending] = useState<boolean>(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      eventsService({ title })
        .then((data: EventInterface[]) => setData(data))
        .catch((error) => setError(error.message))
        .finally(() => setIsPending(false));
    }, 1000);

    return () => clearTimeout(timer);
  }, [title, refreshTrigger]);

  return { data, error, isPending };
};

export default useGetEvents;
