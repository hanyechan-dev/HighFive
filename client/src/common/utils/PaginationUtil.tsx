export const getLastPage = (totalElements:number, elementsPerPage:number) => {
    return Math.ceil(totalElements / elementsPerPage);
};

export const getLastPageBlockIndex = (lastPage:number, pagesPerBlock:number) => {
    return Math.ceil(lastPage / pagesPerBlock) - 1;
};
