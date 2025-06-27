import { Link, useLocation } from "react-router-dom";
import type { LucideIcon } from "lucide-react";

import { Users, BarChart2, MessageSquare, Settings, Home } from "lucide-react";
import { Button } from "../../../components/ui/button";
import { cn } from "../../../lib/utils";

interface NavItem {
  title: string;
  href: string;
  icon: LucideIcon;
}

const navItems: NavItem[] = [
  {
    title: "메인 관리",
    href: "/admin",
    icon: Home,
  },
  {
    title: "회원 관리",
    href: "/dashboard/members",
    icon: Users,
  },
  {
    title: "컨설팅",
    href: "/dashboard/consulting",
    icon: MessageSquare,
  },
  {
    title: "서비스 통계",
    href: "/dashboard/service",
    icon: BarChart2,
  },
  {
    title: "프롬프트 설정",
    href: "/dashboard/prompt",
    icon: Settings,
  },
];

const AdminSidebar = () => {
  const { pathname } = useLocation();

  return (
    <div className="flex h-full w-64 flex-col border-r bg-background">
      <div className="flex h-14 items-center border-b px-4">
        <Link to="/admin" className="flex items-center font-semibold">
          <span className="text-lg text-[#EE57CD]">관리자 대시보드</span>
        </Link>
      </div>
      <nav className="flex-1 overflow-auto p-2">
        <ul className="space-y-1">
          {navItems.map((item) => (
            <li key={item.href}>
              <Button
                asChild
                variant="ghost"
                className={cn(
                  "w-full justify-start text-[#666666]",
                  pathname.startsWith(item.href) && "bg-accent text-[#EE57CD]",
                )}
              >
                <Link to={item.href}>
                  <item.icon className="mr-2 h-4 w-4" />
                  {item.title}
                </Link>
              </Button>
            </li>
          ))}
        </ul>
      </nav>
    </div>
  );
}

export default AdminSidebar;