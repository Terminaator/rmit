"use client";

import React from "react";
import { DataFormDialog } from "@/components/data/FormDialog";
import { createServiceAction } from "@/feature/create-service/action";
import { createServiceFields } from "@/feature/create-service/schema";

export const dialog = {
  title: "Uue teenuse sisestamine",
  form: {
    action: createServiceAction,
    fields: createServiceFields,
  },
};

export const CreateService = () => {
  return <DataFormDialog title={dialog.title} form={dialog.form} />;
};
