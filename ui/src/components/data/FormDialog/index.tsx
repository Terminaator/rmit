"use client";

import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import React, { useState } from "react";
import { Button } from "@/components/ui/button";
import { DataForm } from "@/components/data/Form";
import {
  CreateFormState,
  DataFormFieldType,
} from "@/components/data/Form/type";

type DataFormDialogProps = {
  title: string;
  form: {
    action: (
      // eslint-disable-next-line no-unused-vars
      state: CreateFormState,
      // eslint-disable-next-line no-unused-vars
      form: FormData,
    ) => Promise<CreateFormState>;
    fields: DataFormFieldType[];
  };
};

export const DataFormDialog = ({ title, form }: DataFormDialogProps) => {
  const [open, setOpen] = useState(false);

  return (
    <>
      <Button size="sm" onClick={() => setOpen(!open)}>
        Lisa uus
      </Button>

      <Dialog open={open} onOpenChange={setOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>{title}</DialogTitle>
          </DialogHeader>
          <DataForm
            action={form.action}
            close={() => setOpen(false)}
            fields={form.fields}
          />
        </DialogContent>
      </Dialog>
    </>
  );
};
