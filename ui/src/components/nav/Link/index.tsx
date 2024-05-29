"use client";

import { usePathname } from "next/navigation";
import Link from "next/link";
import { cn } from "@/lib/utils";

type NavLinkProps = {
  name: string;
  href: string;
};

export const NavLink = ({ name, href }: NavLinkProps) => {
  const pathname = usePathname();

  return (
    <Link
      className={cn(
        "flex items-center gap-3 rounded-lg px-3 py-2 transition-all hover:text-primary",
        { "text-muted-foreground": href !== pathname },
        { "bg-muted text-primary": href === pathname },
      )}
      href={href}
    >
      {name}
    </Link>
  );
};
