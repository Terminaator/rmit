import React from "react";

type DataTitleProps = {
  value: string;
  children: React.ReactNode;
};

export const DataTitle = ({ value, children }: DataTitleProps) => {
  return (
    <div className="flex h-14 items-center border-b px-4 lg:h-[60px] lg:px-6 bg-muted/40">
      <h1 className="font-semibold w-full">{value}</h1>
      {children}
    </div>
  );
};
