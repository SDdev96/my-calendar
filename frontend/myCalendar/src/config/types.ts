export interface EventInterface {
  id: number | null;
  title: string;
  description?: string;
  start: string;
  end: string;
}

export interface EventParams {
  title?: string;
}
