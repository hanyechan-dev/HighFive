import type { CertificationResponseDto } from "./MemberPoolApi"

interface CertificationCardProps {
  certification: CertificationResponseDto
}

export function CertificationCard({ certification }: CertificationCardProps) {
  return (
    <div className="border border-gray-200 rounded-md p-3 hover:border-pink-200 transition-colors">
      <div className="font-medium mb-2">{certification.certificationName}</div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-2 text-sm">
        {certification.issuingOrg && (
          <div>
            <span className="text-gray-500">발급기관:</span> {certification.issuingOrg}
          </div>
        )}
        {certification.grade && (
          <div>
            <span className="text-gray-500">등급:</span> {certification.grade}
          </div>
        )}
        {certification.score && (
          <div>
            <span className="text-gray-500">점수:</span> {certification.score}
          </div>
        )}
        {certification.acquisitionDate && (
          <div>
            <span className="text-gray-500">취득일:</span> {certification.acquisitionDate}
          </div>
        )}
        {certification.certificationNo && (
          <div>
            <span className="text-gray-500">자격증 번호:</span> {certification.certificationNo}
          </div>
        )}
      </div>
    </div>
  )
}
