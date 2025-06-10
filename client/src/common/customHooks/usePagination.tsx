import { useState } from 'react';
import { getLastPage, getLastPageBlockIndex } from '../utils/PaginationUtil';


interface UsePaginationProps {
  totalElements: number;
  elementsPerPage: number;
  pagesPerBlock: number;
}

export const usePagination = ({
  totalElements,
  elementsPerPage,
  pagesPerBlock,
}: UsePaginationProps) => {
  const lastPage = getLastPage(totalElements, elementsPerPage);
  const lastPageBlockIndex = getLastPageBlockIndex(lastPage, pagesPerBlock);

  const [clickedPage, setClickedPage] = useState(1);
  const [pageBlockIndex, setPageBlockIndex] = useState(0);

  const onClickFirst = () => {
    setPageBlockIndex(0);
    setClickedPage(1);
  };

  const onClickPrev = () => {
    const newBlock = pageBlockIndex - 1;
    setPageBlockIndex(newBlock);
    setClickedPage(newBlock * pagesPerBlock + pagesPerBlock);
  };

  const onClickNext = () => {
    const newBlock = pageBlockIndex + 1;
    setPageBlockIndex(newBlock);
    setClickedPage(newBlock * pagesPerBlock + 1);
  };

  const onClickLast = () => {
    setPageBlockIndex(lastPageBlockIndex);
    setClickedPage(lastPage);
  };

  return {
    clickedPage,
    pageBlockIndex,
    lastPage,
    lastPageBlockIndex,
    onClickFirst,
    onClickPrev,
    onClickNext,
    onClickLast,
    setClickedPage,
    setPageBlockIndex,
  };
};
