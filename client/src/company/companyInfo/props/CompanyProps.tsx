// 기업정보 관련 타입들 (백엔드 DTO 기반)

export interface MyPageResponseDto {
  email: string;
  name: string;
  birthDate: string;
  genderType: string;
  phone: string;
  address: string;
}

export interface CompanyResponseDto {
  companyName: string;
  industry: string;
  representativeName: string;
  businessNumber: string;
  companyAddress: string;
  companyPhone: string;
  introduction: string;
  companyType: string;
  employeeCount: number;
  establishedDate: string;
  imageUrl: string;
}

export interface CompanyInfoResponse {
  myPageResponseDto: MyPageResponseDto;
  companyResponseDto: CompanyResponseDto;
}

// 결제내역 관련 타입들
export interface PaymentResponseDto {
  paymentId: number;
  id: number;
  paymentAmount: number;
  content: string;
  createdTime: string;
  method: string;
}

export interface PaymentListResponse {
  content: PaymentResponseDto[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
} 