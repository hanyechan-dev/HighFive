import { useState } from "react"
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

// 목업 데이터 1
const userTypeData = [
  { name: "일반회원", value: 65 },
  { name: "기업회원", value: 25 },
  { name: "컨설턴트", value: 10 },
]

// 목업 데이터 2
const subscriptionData = [
  { name: "일반회원", 구독비율: 35, 비구독비율: 65 },
  { name: "기업회원", 구독비율: 70, 비구독비율: 30 },
]

const COLORS = ["#0088FE", "#00C49F", "#FFBB28"]

// 일 단위 목업 데이터 생성
const generateDailyData = (days: number, baseValue: number, variance: number) => {
  const data = []
  const today = new Date()

  for (let i = days - 1; i >= 0; i--) {
    const date = new Date()
    date.setDate(today.getDate() - i)

    const value = baseValue + Math.floor(Math.random() * variance * 2) - variance
    const formattedDate = `${date.getMonth() + 1}/${date.getDate()}`

    data.push({
      date: formattedDate,
      value: Math.max(0, value),
      leave: Math.max(0, baseValue / 5 + Math.floor(Math.random() * variance) - variance / 2),
    })
  }

  return data
}

// 월 단위 목업 데이터 생성
const generateMonthlyData = (months: number, baseValue: number, variance: number) => {
  const data = []
  const today = new Date()

  for (let i = months - 1; i >= 0; i--) {
    const date = new Date()
    date.setMonth(today.getMonth() - i)

    const value = baseValue + Math.floor(Math.random() * variance * 2) - variance
    const formattedDate = `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, "0")}`

    data.push({
      date: formattedDate,
      value: Math.max(0, value),
      leave: Math.max(0, baseValue / 5 + Math.floor(Math.random() * variance) - variance / 2),
    })
  }

  return data
}

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
  const [activeTab, setActiveTab] = useState("users")
  const [periodType, setPeriodType] = useState("30")
  // const [activeIndex, setActiveIndex] = useState(0)

  // 단위 기간에 따른 통계 생성
  const getCurrentData = (baseValue: number, variance: number) => {
    switch (periodType) {
      case "7":
        return generateDailyData(7, baseValue, variance)
      case "30":
        return generateDailyData(30, baseValue, variance)
      case "180": // 6개월
        return generateMonthlyData(6, baseValue * 30, variance * 30)
      case "365": // 1년
        return generateMonthlyData(12, baseValue * 30, variance * 30)
      default:
        return generateDailyData(30, baseValue, variance)
    }
  }

  const regularUserData = getCurrentData(10, 5)
  const corporateUserData = getCurrentData(3, 2)
  const regularRevenueData = getCurrentData(500000, 100000)
  const corporateRevenueData = getCurrentData(1500000, 300000)
  const consultingCountData = getCurrentData(15, 5)

  const handlePeriodChange = (period: string) => {
    setPeriodType(period)
  }

  // const onPieEnter = (_: any, index: number) => {
  //   setActiveIndex(index)
  // }

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
                      label={({ name, percent }) => `${name}: ${((percent ?? 0) * 100).toFixed(0)}%`}
                      outerRadius={100}
                      fill="#8884d8"
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
                    variant={periodType === "7" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("7")}
                    className={periodType === "7" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === "30" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("30")}
                    className={periodType === "30" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === "180" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("180")}
                    className={periodType === "180" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === "365" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("365")}
                    className={periodType === "365" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>

              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    data={regularUserData}
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
            </CardContent>
          </Card>

          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">기업회원 가입 및 탈퇴 통계</h2>
              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    data={corporateUserData}
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
                  <div className="flex-grow">
                    <ResponsiveContainer width="100%" height="100%">
                      <PieChart>
                        <Pie
                        //   activeIndex={activeIndex}
                          activeShape={renderActiveShape}
                          data={[
                            { name: "구독", value: subscriptionData[0].구독비율, fill: "#EE57CD" },
                            { name: "비구독", value: subscriptionData[0].비구독비율, fill: "#FFB6E1" },
                          ]}
                          cx="50%"
                          cy="50%"
                          innerRadius={60}
                          outerRadius={80}
                          dataKey="value"
                          // onMouseEnter={onPieEnter}
                        />
                        <Tooltip />
                      </PieChart>
                    </ResponsiveContainer>
                  </div>
                  <div className="text-center mt-4">
                    <p className="text-sm text-[#666666]">
                      총 일반회원 수: <span className="font-semibold">1,234명</span>
                    </p>
                    <p className="text-sm text-[#666666]">
                      구독 회원 수: <span className="font-semibold">432명</span>
                    </p>
                  </div>
                </div>
                <div className="h-[400px] flex flex-col">
                  <h3 className="text-lg font-medium text-center mb-2 text-[#666666]">기업회원 구독 현황</h3>
                  <div className="flex-grow">
                    <ResponsiveContainer width="100%" height="100%">
                      <PieChart>
                        <Pie
                        //   activeIndex={activeIndex + 2}
                          activeShape={renderActiveShape}
                          data={[
                            { name: "구독", value: subscriptionData[1].구독비율, fill: "#8884d8" },
                            { name: "비구독", value: subscriptionData[1].비구독비율, fill: "#B8B5E1" },
                          ]}
                          cx="50%"
                          cy="50%"
                          innerRadius={60}
                          outerRadius={80}
                          dataKey="value"
                          // onMouseEnter={(_, index) => setActiveIndex(index + 2)}
                        />
                        <Tooltip />
                      </PieChart>
                    </ResponsiveContainer>
                  </div>
                  <div className="text-center mt-4">
                    <p className="text-sm text-[#666666]">
                      총 기업회원 수: <span className="font-semibold">456명</span>
                    </p>
                    <p className="text-sm text-[#666666]">
                      구독 회원 수: <span className="font-semibold">319명</span>
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
                    variant={periodType === "7" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("7")}
                    className={periodType === "7" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === "30" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("30")}
                    className={periodType === "30" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === "180" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("180")}
                    className={periodType === "180" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === "365" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("365")}
                    className={periodType === "365" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>

              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    data={regularRevenueData}
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
            </CardContent>
          </Card>

          <Card>
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4 text-[#666666]">기업회원 매출 통계</h2>
              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    data={corporateRevenueData}
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
                    variant={periodType === "7" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("7")}
                    className={periodType === "7" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    7일
                  </Button>
                  <Button
                    variant={periodType === "30" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("30")}
                    className={periodType === "30" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1달
                  </Button>
                  <Button
                    variant={periodType === "180" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("180")}
                    className={periodType === "180" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    6개월
                  </Button>
                  <Button
                    variant={periodType === "365" ? "default" : "outline"}
                    onClick={() => handlePeriodChange("365")}
                    className={periodType === "365" ? "bg-[#EE57CD] hover:bg-[#d33eb3]" : ""}
                  >
                    1년
                  </Button>
                </div>
              </div>

              <div className="h-[300px]">
                <ResponsiveContainer width="100%" height="100%">
                  <LineChart
                    data={consultingCountData}
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
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  )
}
