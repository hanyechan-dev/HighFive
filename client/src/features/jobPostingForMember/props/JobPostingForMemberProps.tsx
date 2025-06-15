export interface JobPostingSummaryForMemberDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	job: string;
	workLocation: string;
	careerType: string;
	educationLevel: string;
	similarityScore: number;
	createdDate: string;
}

export interface JobPostingMainCardDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	job: string;
	workLocation: string;
	careerType: string;
	educationLevel: string;
	similarityScore: number;
	createdDate: string;
	imageUrl: string;
}


export interface JobPostingForMemberResponseDto {
	id: number;
	title: string;
	companyName: string;
	companyType: string;
	workingHours: string;
	workLocation: string;
	job: string;
	careerType: string;
	educationLevel: string;
	salary: number;
	content: string;
	requirement: string;
	imageUrls: string[];
	createdDate: string;
	expiredDate: string;
}