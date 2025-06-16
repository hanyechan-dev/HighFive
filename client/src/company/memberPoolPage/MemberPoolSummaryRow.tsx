import type { MemberPoolSummary } from "./MemberPoolApi";

interface MemberPoolSummaryRowProps {
  member: MemberPoolSummary;
  onClick: (id: number) => void;
}

export default function MemberPoolSummaryRow({ member, onClick }: MemberPoolSummaryRowProps) {
  return (
    <div className="flex" color="red">
    <tr
      className="hover:bg-gray-100 cursor-pointer"
      onClick={() => onClick(member.id)}
    >
      <td>{member.name}</td>
      <td>{member.job}</td>
      <td>{member.hasCareer ? "경력" : "신입"}</td>
      <td>{member.similarityScore}%</td>
      <td>{member.educationLevel}</td>
    </tr>
    </div>
  );
} 