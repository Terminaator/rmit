"use client";

import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import React, { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import { DataForm } from "@/components/data/Form";
import { UseFormReturn } from "react-hook-form";
import { SUCCESS } from "@/components/data/Form/constant";
import { DataFormState } from "@/components/data/Form/type";
import { DataFormFieldType } from "@/components/data/FormField";

type DataDialogProps = {
  title: string;
  // eslint-disable-next-line no-unused-vars
  submit: (state: DataFormState, form: FormData) => Promise<DataFormState>;
  form: UseFormReturn<any, any, any>;
  fields: DataFormFieldType[];
};

export const DataDialog = ({
  title,
  submit,
  form,
  fields,
}: DataDialogProps) => {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (!open) {
      form.reset();
    }
  }, [open, form]);

  const onSubmit = async (state: DataFormState, form: FormData) => {
    const nextState = await submit(state, form);
    if (nextState.status === SUCCESS) {
      setOpen(false);
    }
    return nextState;
  };

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
          <DataForm submit={onSubmit} form={form} fields={fields} />
        </DialogContent>
      </Dialog>
    </>
  );
};
