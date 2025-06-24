import type { ReactNode } from "react";

interface PageTitleProps {
  title: string;
  description?: string;
  children?: ReactNode;
}

export default function PageTitle({ title, description, children }: PageTitleProps) {
  return (
    <div>
      <h1 className="text-3xl font-bold bg-gradient-to-r from-theme to-pink-500 bg-clip-text text-transparent font-roboto">
        {title}
      </h1>
      {description && (
        <p className="text-gray-500 mt-2 font-roboto">{description}</p>
      )}
      {children}
    </div>
  );
} 