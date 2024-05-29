import React from "react";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";

export const DataAlert = ({ message }: { message: string }) => {
  return (
    <Alert variant="destructive">
      <AlertTitle>Viga</AlertTitle>
      <AlertDescription>{message}</AlertDescription>
    </Alert>
  );
};
