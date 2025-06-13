import { type LanguageTestResponseDto } from "./MemberPoolApi"

interface LanguageCardProps {
  languageTest: LanguageTestResponseDto
}

export function LanguageCard({ languageTest }: LanguageCardProps) {
  return (
    <div className="border border-gray-200 rounded-md p-3 hover:border-pink-200 transition-colors">
      <div className="font-medium mb-2">
        {languageTest.languageType} - {languageTest.testName}
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-2 text-sm">
        {languageTest.issuingOrg && (
          <div>
            <span className="text-gray-500">발급기관:</span> {languageTest.issuingOrg}
          </div>
        )}
        {languageTest.score && (
          <div>
            <span className="text-gray-500">점수:</span> {languageTest.score}
          </div>
        )}
        {languageTest.grade && (
          <div>
            <span className="text-gray-500">등급:</span> {languageTest.grade}
          </div>
        )}
        {languageTest.acquisitionDate && (
          <div>
            <span className="text-gray-500">취득일:</span> {languageTest.acquisitionDate}
          </div>
        )}
      </div>
    </div>
  )
}
