"use client";

import { Search } from "lucide-react";
import { Input } from "@/components/ui/input";
import React from "react";
import { usePathname, useRouter, useSearchParams } from "next/navigation";

const SEARCH_PARAM_NAME = "name";

type DataSearchProps = {
  placeholder: string;
};

export const DataSearch = ({ placeholder }: DataSearchProps) => {
  const { replace } = useRouter();
  const pathname = usePathname();
  const searchParams = useSearchParams();

  const handleSearch = (value: string) => {
    const params = new URLSearchParams(searchParams);
    value
      ? params.set(SEARCH_PARAM_NAME, value)
      : params.delete(SEARCH_PARAM_NAME);
    replace(`${pathname}?${params.toString()}`);
  };

  return (
    <div className="relative">
      <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
      <Input
        defaultValue={searchParams.get(SEARCH_PARAM_NAME)?.toString()}
        onChange={(e) => handleSearch(e.target.value)}
        type="search"
        placeholder={placeholder}
        className="pl-8"
      />
    </div>
  );
};
