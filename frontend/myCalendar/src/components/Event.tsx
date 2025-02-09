import { Card, Stack, Text } from "@mantine/core";
import { EventInterface } from "../config/types";

import "./event.css";

interface EventProps extends EventInterface {
  onClick: () => void;
}

const Event = ({ id, title, description, start, end, onClick }: EventProps) => {

  return (
    <Card key={id} className="event-item" onClick={onClick} shadow="sm" p="md" radius="md" withBorder>
      <Stack h={100} align="stretch" justify="flex-start" gap="xs">
        <Text
          size="xl"
          fw={700}
          variant="gradient"
          gradient={{ from: "blue", to: "orange", deg: 90 }}
        >
          {title}
        </Text>
        <Text size="xs">
          {description ? (
            description
          ) : (
            <Text span c="dimmed">
              No description
            </Text>
          )}
        </Text>
      </Stack>

      <Text size="sm">
        <Text span c="dimmed">
          Data inizio:{" "}
        </Text>
        <Text span size="sm" fw={500}>
          {new Date(start).toLocaleString().slice(0, 17)}
        </Text>
      </Text>

      <Text size="sm">
        <Text span c="dimmed">
          Data fine:{" "}
        </Text>
        <Text span size="sm" fw={500}>
          {new Date(end).toLocaleString().slice(0, 17)}
        </Text>
      </Text>
    </Card>
  );
};

export default Event;
