interface SearchProps{
    keyword : string;
    setKeyword: (value: string) => void;
    onSearch: () => void;
}

const Search = ({
  keyword,
  setKeyword,
  onSearch,
}: SearchProps) => {
  return (
    <div className="flex items-center gap-4">
      <input
        type="text"
        value={keyword}
        onChange={function (e): void {
          setKeyword(e.target.value);
        }}
        placeholder="검색"
        className="border rounded px-3 py-2 w-[300px]"
      />
     
    </div>
  );
};

export default Search;
