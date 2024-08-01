export const timeFormatter = (time) => {
  const kor = new Date(time);
  kor.setHours(kor.getHours() + 9);

  return kor;
};
