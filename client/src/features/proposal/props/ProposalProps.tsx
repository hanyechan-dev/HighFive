export interface ProposalSummary {
  id: number;
  name: string;
  genderType: string;
  birthDate: string;
  hasCareer: boolean;
  job: string;
  educationLevel: string;
  proposalDate: string;
  proposalStatus: string;
}

export interface ProposalDetail {
  id: number;
  proposalTitle: string;
  companyName: string;
  proposalContent: string;
  proposalJob: string;
  proposalSalary: number;
  proposalDate: string;
  proposalStatus: string;
} 