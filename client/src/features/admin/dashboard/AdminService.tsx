import { useEffect, useState } from "react"
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
  PieChart,
  Pie,
  Cell,
  Sector
} from "recharts"
import { Card, CardContent } from "../../../components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "../../../components/ui/tabs"
import { Button } from "../../../components/ui/button"
import axios from "axios"
import { useSelector } from "react-redux"
import type { RootState } from "../../../common/store/store"
import { BeatLoader } from "react-spinners"

interface SubsCount {
  userType: string;
  subs: number;
  unSubs: number;
}

interface DayState {
  date: string;
  signUps: number;
  withdraws: number;
}

interface MonthState {
  month: number;
  signUps: number;
  withdraws: number;
}

interface DataFormat {
  date: string;
  value: number;
  leave: number;
}

interface SalesData {
  date: string;
  payAmount: number;
}

interface consultingData {
  date: string;
  consultCount: number; 
}

const COLORS = ["#0088FE", "#00C49F", "#FFBB28"]

// 활성 항목 상태를 위한 함수
const renderActiveShape = (props: any) => {
  const RADIAN = Math.PI / 180
  const { cx, cy, midAngle, innerRadius, outerRadius, startAngle, endAngle, fill, payload, percent, value } = props
  const sin = Math.sin(-RADIAN * midAngle)
  const cos = Math.cos(-RADIAN * midAngle)
  const sx = cx + (outerRadius + 10) * cos
  const sy = cy + (outerRadius + 10) * sin
  const mx = cx + (outerRadius + 30) * cos
  const my = cy + (outerRadius + 30) * sin
  const ex = mx + (cos >= 0 ? 1 : -1) * 22
  const ey = my
  const textAnchor = cos >= 0 ? "start" : "end"

  return (
    <g>
      <text x={cx} y={cy} dy={8} textAnchor="middle" fill={fill}>
        {payload.name}
      </text>
      <Sector
        cx={cx}
        cy={cy}
        innerRadius={innerRadius}
        outerRadius={outerRadius}
        startAngle={startAngle}
        endAngle={endAngle}
        fill={fill}
      />
      <Sector
        cx={cx}
        cy={cy}
        startAngle={startAngle}
        endAngle={endAngle}
        innerRadius={outerRadius + 6}
        outerRadius={outerRadius + 10}
        fill={fill}
      />
      <path d={`M${sx},${sy}L${mx},${my}L${ex},${ey}`} stroke={fill} fill="none" />
      <circle cx={ex} cy={ey} r={2} fill={fill} stroke="none" />
      <text x={ex + (cos >= 0 ? 1 : -1) * 12} y={ey} textAnchor={textAnchor} fill="#333">{`${value}명`}</text>
      <text x={ex + (cos >= 0 ? 1 : -1) * 12} y={ey} dy={18} textAnchor={textAnchor} fill="#999">
        {`(${(percent * 100).toFixed(0)}%)`}
      </text>
    </g>
  )
}

export default function StatisticsPage() {

  const token = useSelector((state: RootState) => state.auth.accessToken)

  const [userTypeData, setUserTypeData] = useState([])
  const [subscriptionData, setSubscriptionData] = useState<SubsCount[]>([])

  const [userDayState, setUserDayState] = useState<DayState[]>([])
  const [userMonthState, setUserMonthState] = useState<MonthState[]>([])
  const [companyDayState, setCompanyDayState] = useState<DayState[]>([])
  const [companyMonthState, setCompanyMonthState] = useState<MonthState[]>([])
  const [userSalesState, setUserSalesState] = useState<SalesData[]>([])
  const [companySalesState, setCompanySalesState] = useState<SalesData[]>([])
  const [consultingState, setConsultingState] = useState<consultingData[]>([])

  const [activeTab, setActiveTab] = useState("users")
  const [periodType, setPeriodType] = useState<number>(30)

  const [subsStateIsLoading, setSubsStateIsLoading] = useState<boolean>(true)
  const [userStateIsLoading, setUserStateIsLoading] = useState<boolean>(true)
  const [companyStateIsLoading, setCompanyStateIsLoading] = useState<boolean>(true)
  const [userSalesIsLoading, setUserSalesIsLoading] = useState<boolean>(true)
  const [companySalesIsLoading, setCompanySalesIsLoading] = useState<boolean>(true)
  const [consultingIsLoading, setConsultingIsLoading] = useState<boolean>(true)

  let userStateData
  let companyStateData
  let userSalesData
  let companySalesData
  let consultingData

  const handlePeriodChange = (period: number) => {
    setPeriodType(period)
  }

  // 통계 데이터 불러오기
  useEffect(() => {

    // 회원 유형별 이용자 비율
    const fetchUserCount = async () => {
      try {
        const response = await axios.get("http://localhost:8090/admin/service/count-users", {
          headers: { Authorization: `Bearer ${token}` }
        });
        console.log(response.data);
        setUserTypeData(response.data);
      } catch (error) {
        console.log("각 UserType의 총 회원 불러오기 실패: ", error);
      }
    }

    // 회원 유형별 구독 현황
    const fetchSubsCount = async () => {
      try {
        const response = await axios.get("http://localhost:8090/admin/service/count-subs", {
          headers: { Authorization: `Bearer ${token}` }
        });
        console.log(response.data);
        setSubscriptionData(response.data);
      } catch (error) {
        console.log("UserType별 구독자 현황 불러오기 실패: ", error);
      } finally {
        setSubsStateIsLoading(false);
      }
    }
    fetchUserCount();
    fetchSubsCount();
  }, [])

  // 일반회원, 기업회원 가입 및 탈퇴 데이터 불러오기
  useEffect(() => {
    const fetchSignUpAndWithdrawal = async (period: number) => {
      try { // 단위기간이 일(day) 단위일 시
        if (period == 7 || period == 30) {
          const [userData, companyData] = await Promise.all([
            axios.get(`http://localhost:8090/admin/service/reg-and-cancel/day?days=${period}&userType=일반회원`,
            { headers: { Authorization: `Bearer ${token}` } }),
            axios.get(`http://localhost:8090/admin/service/reg-and-cancel/day?days=${period}&userType=기업회원`,
            { headers: { Authorization: `Bearer ${token}` } })
          ])
          console.log(userData.data);
          console.log(companyData.data);

          setUserDayState(userData.data);
          setCompanyDayState(companyData.data);

        } else {  // 단위기간이 월(month) 단위일 시
          const [userData, companyData] = await Promise.all([
            axios.get(`http://localhost:8090/admin/service/reg-and-cancel/month?month=${period}&userType=일반회원`,
            { headers: { Authorization: `Bearer ${token}` } }),
            axios.get(`http://localhost:8090/admin/service/reg-and-cancel/month?month=${period}&userType=기업회원`,
            { headers: { Authorization: `Bearer ${token}` } })
          ])
          console.log(userData.data);
          console.log(companyData.data);

          setUserMonthState(userData.data);
          setCompanyMonthState(companyData.data);
        }
      } catch (error) {
        setUserDayState([]);
        setCompanyDayState([]);
        setUserStateIsLoading(false);
        setCompanyStateIsLoading(false);
        console.log("일반회원 가입 및 탈퇴 통계 데이터 불러오기 실패: ", error);
      }
    }
    fetchSignUpAndWithdrawal(periodType);
  }, [periodType])

  // 일반회원 가입 및 탈퇴 통계 데이터 생성
  const generateUserStateData = (period: number) : DataFormat[] => {
    try {
      const data : DataFormat[] = [];
      const today = new Date();

      if (period == 7 || period == 30) { // 선택된 단위기간의 단위가 일(day)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setDate(today.getDate() - i)

          const [, month, day] = userDayState[period - (i + 1)].date.split("-");
          const splittedDate = `${parseInt(month)}/${parseInt(day)}`; // 서버로부터 받은 데이터를 화면에 출력할 수 있게 포매팅
          const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            });
          } else {
            data.push({
              date: splittedDate,
              value: userDayState[period - (i + 1)].signUps,
              leave: userDayState[period - (i + 1)].withdraws
            });
          }
        }
      } else {  // 선택된 단위기간의 단위가 달(month)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setMonth(today.getMonth() - i)

          const combinedData = `${date.getFullYear()}-${userMonthState[period - (i + 1)].month.toString().padStart(2, "0")}`;
          const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

          if (combinedData !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            })
          } else {
            data.push({
              date: combinedData,
              value: userMonthState[period - (i + 1)].signUps,
              leave: userMonthState[period - (i + 1)].withdraws
            })
          }
        }
      }
      return data;
    } catch (error) {
      console.log("일반회원 가입 및 탈퇴 통계 데이터 생성 실패 :", error)
      return [];
    }
  }

  // 기업회원 가입 및 탈퇴 통계 데이터 생성
  const generateCompanyStateData = (period: number) : DataFormat[] => {
    try {
      const data : DataFormat[] = [];
      const today = new Date();

      if (period == 7 || period == 30) { // 선택된 단위기간의 단위가 일(day)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setDate(today.getDate() - i)

          const [, month, day] = companyDayState[period - (i + 1)].date.split("-");
          const splittedDate = `${parseInt(month)}/${parseInt(day)}`; // 서버로부터 받은 데이터를 화면에 출력할 수 있게 포매팅
          const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            });
          } else {
            data.push({
              date: splittedDate,
              value: companyDayState[period - (i + 1)].signUps,
              leave: companyDayState[period - (i + 1)].withdraws
            });
          }
        }
      } else {  // 선택된 단위기간의 단위가 달(month)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setMonth(today.getMonth() - i)

          const combinedData = `${date.getFullYear()}-${companyMonthState[period - (i + 1)].month.toString().padStart(2, "0")}`;
          const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

          if (combinedData !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            })
          } else {
            data.push({
              date: combinedData,
              value: companyMonthState[period - (i + 1)].signUps,
              leave: companyMonthState[period - (i + 1)].withdraws
            })
          }
        }
      }
      return data;
    } catch (error) {
      console.log("기업회원 가입 및 탈퇴 통계 데이터 생성 실패 :", error)
      return [];
    }
  }

  // 상기 통계 데이터 상태 반영 완료 시, 로딩을 종료하고 화면에 통계 출력(일반회원 기준)
  useEffect(() => {
    userStateData = generateUserStateData(periodType);

    if(userStateData != undefined && userStateData != null){
      setUserStateIsLoading(false);
    }
  }, [userDayState, userMonthState, periodType])

  // 상기 통계 데이터 상태 반영 완료 시, 로딩을 종료하고 화면에 통계 출력(기업회원 기준)
  useEffect(() => {
    companyStateData = generateCompanyStateData(periodType);

    if(companyStateData != undefined && companyStateData != null){
      setCompanyStateIsLoading(false);
    }
  }, [companyDayState, companyMonthState, periodType])

  // 일반회원, 기업회원 매출 데이터 불러오기
  useEffect(() => {
    const fetchSales = async (period: number) => {
      try {
        const [userData, companyData] = await Promise.all([
          axios.get(`http://localhost:8090/admin/service/count-payment?period=${period}&userType=일반회원`,
            { headers: { Authorization: `Bearer ${token}` } }),
          axios.get(`http://localhost:8090/admin/service/count-payment?period=${period}&userType=기업회원`,
            { headers: { Authorization: `Bearer ${token}` } })
        ])
          console.log(userData.data);
          console.log(companyData.data);

          setUserSalesState(userData.data);
          setCompanySalesState(companyData.data);
      } catch (error) {
        console.log("일반회원, 기업회원 매출 통계 데이터 불러오기 실패: ", error);
      }
    }
    fetchSales(periodType);
  }, [periodType])

  // 일반회원 매출 통계 데이터 생성
  const generateUserSalesData = (period: number) : DataFormat[] => {
    try {
      const data : DataFormat[] = [];
      const today = new Date();

      if (period == 7 || period == 30) { // 선택된 단위기간의 단위가 일(day)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setDate(today.getDate() - i)

          const [, month, day] = userSalesState[period - (i + 1)].date.split("-");
          const splittedDate = `${parseInt(month)}/${parseInt(day)}`; // 서버로부터 받은 데이터를 화면에 출력할 수 있게 포매팅
          const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            });
          } else {
            data.push({
              date: splittedDate,
              value: userSalesState[period - (i + 1)].payAmount,
              leave: 0
            });
          }
        }
      } else {  // 선택된 단위기간의 단위가 달(month)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setMonth(today.getMonth() - i)

          const [year, month, ] = userSalesState[period - (i + 1)].date.split("-");
          const splittedDate = `${year}-${month}`;
          const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            })
          } else {
            data.push({
              date: splittedDate,
              value: userSalesState[period - (i + 1)].payAmount,
              leave: 0
            })
          }
        }
      }
      return data;
    } catch (error) {
      console.log("일반회원 매출 통계 데이터 생성 실패 :", error)
      return [];
    }
  }

  // 기업회원 매출 통계 데이터 생성
  const generateCompanySalesData = (period: number) : DataFormat[] => {
    try {
      const data : DataFormat[] = [];
      const today = new Date();

      if (period == 7 || period == 30) { // 선택된 단위기간의 단위가 일(day)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setDate(today.getDate() - i)

          const [, month, day] = companySalesState[period - (i + 1)].date.split("-");
          const splittedDate = `${parseInt(month)}/${parseInt(day)}`; // 서버로부터 받은 데이터를 화면에 출력할 수 있게 포매팅
          const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            });
          } else {
            data.push({
              date: splittedDate,
              value: companySalesState[period - (i + 1)].payAmount,
              leave: 0
            });
          }
        }
      } else {  // 선택된 단위기간의 단위가 달(month)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setMonth(today.getMonth() - i)

          const [year, month, ] = companySalesState[period - (i + 1)].date.split("-");
          const splittedDate = `${year}-${month}`;
          const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            })
          } else {
            data.push({
              date: splittedDate,
              value: companySalesState[period - (i + 1)].payAmount,
              leave: 0
            })
          }
        }
      }
      return data;
    } catch (error) {
      console.log("일반회원 매출 통계 데이터 생성 실패 :", error)
      return [];
    }
  }

  // 상기 통계 데이터 상태 반영 완료 시, 로딩을 종료하고 화면에 통계 출력
  useEffect(() => {
    userSalesData = generateUserSalesData(periodType);
    
    if(userSalesData != undefined && userSalesData != null){
      setUserSalesIsLoading(false);
    }
  }, [userSalesState, periodType])

    // 상기 통계 데이터 상태 반영 완료 시, 로딩을 종료하고 화면에 통계 출력
  useEffect(() => {
    companySalesData = generateCompanySalesData(periodType);

    if(companySalesData != undefined && companySalesData != null){
      setCompanySalesIsLoading(false);
    }
  }, [companySalesState, periodType])

  // 컨설팅 집계 데이터 불러오기
  useEffect(() => {
    const fetchConsulting = async (period: number) => {
      try {
          const consultings = await axios.get(`http://localhost:8090/admin/service/count-consulting?period=${period}`, {
            headers: { Authorization: `Bearer ${token}` }
          });
          console.log(consultings.data);

          setConsultingState(consultings.data);
      } catch (error) {
        console.log("컨설팅 통계 데이터 불러오기 실패: ", error);
      }
    }
    fetchConsulting(periodType);
  }, [periodType])

  // 컨설팅 통계 데이터 생성
  const generateConsultingData = (period: number) : DataFormat[] => {
    try {
      const data : DataFormat[] = [];
      const today = new Date();

      if (period == 7 || period == 30) { // 선택된 단위기간의 단위가 일(day)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setDate(today.getDate() - i)

          const [, month, day] = consultingState[period - (i + 1)].date.split("-");
          const splittedDate = `${parseInt(month)}/${parseInt(day)}`; // 서버로부터 받은 데이터를 화면에 출력할 수 있게 포매팅
          const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            });
          } else {
            data.push({
              date: splittedDate,
              value: consultingState[period - (i + 1)].consultCount,
              leave: 0
            });
          }
        }
      } else {  // 선택된 단위기간의 단위가 달(month)일 때
        for (let i = period - 1; i >= 0; i--) {
          const date = new Date()
          date.setMonth(today.getMonth() - i)

          const [year, month, ] = consultingState[period - (i + 1)].date.split("-");
          const splittedDate = `${year}-${month}`;
          const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

          if (splittedDate !== formattedDate) {
            data.push({
              date: formattedDate,
              value: 0,
              leave: 0
            })
          } else {
            data.push({
              date: splittedDate,
              value: consultingState[period - (i + 1)].consultCount,
              leave: 0
            })
          }
        }
      }
      return data;
    } catch (error) {
      console.log("컨설팅 통계 데이터 생성 실패 :", error)
      return [];
    }
  }

  // 상기 통계 데이터 상태 반영 완료 시, 로딩을 종료하고 화면에 통계 출력
  useEffect(() => {
    consultingData = generateConsultingData(periodType);

    if(consultingData != undefined && consultingData != null){
      setConsultingIsLoading(false);
    }
  }, [consultingState, periodType])

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold text-[#EE57CD]">서비스 통계</h1>

      <Tabs value={activeTab} onValueChange={setActiveTab}>
        <TabsList className="grid w-full max-w-2xl grid-cols-4">
          <TabsTrigger value="users">이용자 통계</TabsTrigger>
          <TabsTrigger value="subscription">서비스 구독 현황</TabsTrigger>
          <TabsTrigger value="revenue">매출 통계</TabsTrigger>
          <TabsTrigger value="consulting">컨설팅 통계</TabsTrigger>
        </TabsList>

        <TabsContent value="users" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">회원 유형별 이용자 비율</h2>
              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <PieChart>
                    <Pie
                      data={userTypeData}
                      cx="50%"
                      cy="50%"
                      labelLine={true}
                      label={({ userType, percent }) => `${userType}: ${((percent ?? 0) * 100).toFixed(0)}%`}
                      outerRadius={100}
                      fill="#8884d8"
                      nameKey="userType"
                      dataKey="value"
                    >
                      {userTypeData.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                      ))}
                    </Pie>
                    <Tooltip />
                    <Legend />
                  </PieChart>
                </ResponsiveContainer>
              </div>
            </CardContent>
          </Card>

          <Card>
            <CardContent className="pt-6">
              <div className="flex flex-wrap justify-between items-center mb-4">
                <h2 className="text-xl font-semibold text-[#666666]">일반회원 가입 및 탈퇴 통계</h2>
                <div className="flex space-x-2">
                  <Button
                    variant={periodType === 7 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(7)}
                    className={periodType === 7 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === 30 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(30)}
                    className={periodType === 30 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === 6 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(6)}
                    className={periodType === 6 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === 12 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(12)}
                    className={periodType === 12 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>
              {userStateIsLoading ? <BeatLoader /> :
                <div className="h-[300px]">
                  <ResponsiveContainer width="100%" height="100%">
                    <LineChart
                      data={userStateData}
                      margin={{
                        top: 5,
                        right: 30,
                        left: 20,
                        bottom: 5,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="date" />
                      <YAxis />
                      <Tooltip />
                      <Legend />
                      <Line type="monotone" dataKey="value" name="가입" stroke="#EE57CD" activeDot={{ r: 8 }} />
                      <Line type="monotone" dataKey="leave" name="탈퇴" stroke="#ff7300" />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
              }
            </CardContent>
          </Card>

          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">기업회원 가입 및 탈퇴 통계</h2>
              {companyStateIsLoading ? <BeatLoader /> :
                <div className="h-[300px]">
                  <ResponsiveContainer width="100%" height="100%">
                    <LineChart
                      data={companyStateData}
                      margin={{
                        top: 5,
                        right: 30,
                        left: 20,
                        bottom: 5,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="date" />
                      <YAxis />
                      <Tooltip />
                      <Legend />
                      <Line type="monotone" dataKey="value" name="가입" stroke="#EE57CD" activeDot={{ r: 8 }} />
                      <Line type="monotone" dataKey="leave" name="탈퇴" stroke="#ff7300" />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
              }
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="subscription" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">회원 유형별 구독 현황</h2>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div className="h-[400px] flex flex-col">
                  <h3 className="text-lg font-medium text-center mb-2 text-[#666666]">일반회원 구독 현황</h3>
                  {subsStateIsLoading ? <BeatLoader /> :
                    <div className="flex-grow">
                      <ResponsiveContainer width="100%" height="100%">
                        <PieChart>
                          <Pie
                            activeShape={renderActiveShape}
                            data={[
                              { name: "구독", value: subscriptionData[0].subs, fill: "#EE57CD" },
                              { name: "비구독", value: subscriptionData[0].unSubs, fill: "#FFB6E1" },
                            ]}
                            cx="50%"
                            cy="50%"
                            innerRadius={60}
                            outerRadius={80}
                            dataKey="value"
                          />
                          <Tooltip />
                        </PieChart>
                      </ResponsiveContainer>
                    </div>
                  }

                  <div className="text-center mt-4">
                    <p className="text-sm text-[#666666]">
                      총 일반회원 수: <span className="font-semibold">{(subscriptionData[0]?.subs ?? 0) + (subscriptionData[0]?.unSubs ?? 0)}명</span>
                    </p>
                    <p className="text-sm text-[#666666]">
                      구독 회원 수: <span className="font-semibold">{(subscriptionData[0]?.subs ?? 0)}명</span>
                    </p>
                  </div>
                </div>
                <div className="h-[400px] flex flex-col">
                  <h3 className="text-lg font-medium text-center mb-2 text-[#666666]">기업회원 구독 현황</h3>
                  {subsStateIsLoading ? <BeatLoader /> :
                    <div className="flex-grow">
                      <ResponsiveContainer width="100%" height="100%">
                        <PieChart>
                          <Pie
                            activeShape={renderActiveShape}
                            data={[
                              { name: "구독", value: subscriptionData[1].subs, fill: "#8884d8" },
                              { name: "비구독", value: subscriptionData[1].unSubs, fill: "#B8B5E1" },
                            ]}
                            cx="50%"
                            cy="50%"
                            innerRadius={60}
                            outerRadius={80}
                            dataKey="value"
                          />
                          <Tooltip />
                        </PieChart>
                      </ResponsiveContainer>
                    </div>
                  }

                  <div className="text-center mt-4">
                    <p className="text-sm text-[#666666]">
                      총 기업회원 수: <span className="font-semibold">{(subscriptionData[1]?.subs ?? 0) + (subscriptionData[1]?.unSubs ?? 0)}명</span>
                    </p>
                    <p className="text-sm text-[#666666]">
                      구독 회원 수: <span className="font-semibold">{(subscriptionData[1]?.subs ?? 0)}명</span>
                    </p>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="revenue" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <div className="flex flex-wrap justify-between items-center mb-4">
                <h2 className="text-xl font-semibold text-[#666666]">일반회원 매출 통계</h2>
                <div className="flex space-x-2">
                  <Button
                    variant={periodType === 7 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(7)}
                    className={periodType === 7 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === 30 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(30)}
                    className={periodType === 30 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === 6 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(6)}
                    className={periodType === 6 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === 12 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(12)}
                    className={periodType === 12 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>
              {userSalesIsLoading ? <BeatLoader /> :
                <div className="h-[300px]">
                  <ResponsiveContainer width="100%" height="100%">
                    <LineChart
                      data={userSalesData}
                      margin={{
                        top: 5,
                        right: 30,
                        left: 20,
                        bottom: 5,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="date" />
                      <YAxis />
                      <Tooltip formatter={(value) => [`₩${value.toLocaleString()}`, "매출"]} />
                      <Legend />
                      <Line type="monotone" dataKey="value" name="매출" stroke="#EE57CD" activeDot={{ r: 8 }} />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
              }
            </CardContent>
          </Card>

          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">기업회원 매출 통계</h2>
              {companySalesIsLoading ? <BeatLoader /> :
                <div className="h-[300px]">
                  <ResponsiveContainer width="100%" height="100%">
                    <LineChart
                      data={companySalesData}
                      margin={{
                        top: 5,
                        right: 30,
                        left: 20,
                        bottom: 5,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="date" />
                      <YAxis />
                      <Tooltip formatter={(value) => [`₩${value.toLocaleString()}`, "매출"]} />
                      <Legend />
                      <Line type="monotone" dataKey="value" name="매출" stroke="#EE57CD" activeDot={{ r: 8 }} />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
              }
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="consulting" className="space-y-4">
          <Card>
            <CardContent className="pt-6">
              <div className="flex flex-wrap justify-between items-center mb-4">
                <h2 className="text-xl font-semibold text-[#666666]">컨설팅 통계</h2>
                <div className="flex space-x-2">
                  <Button
                    variant={periodType === 7 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(7)}
                    className={periodType === 7 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === 30 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(30)}
                    className={periodType === 30 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === 6 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(6)}
                    className={periodType === 6 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === 12 ? "default" : "outline"}
                    onClick={() => handlePeriodChange(12)}
                    className={periodType === 12 ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>
              {consultingIsLoading ? <BeatLoader /> :
                <div className="h-[300px]">
                  <ResponsiveContainer width="100%" height="100%">
                    <LineChart
                      data={consultingData}
                      margin={{
                        top: 5,
                        right: 30,
                        left: 20,
                        bottom: 5,
                      }}
                    >
                      <CartesianGrid strokeDasharray="3 3" />
                      <XAxis dataKey="date" />
                      <YAxis />
                      <Tooltip />
                      <Legend />
                      <Line type="monotone" dataKey="value" name="컨설팅 수" stroke="#EE57CD" activeDot={{ r: 8 }} />
                    </LineChart>
                  </ResponsiveContainer>
                </div>
              }
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  )
}
