import { useState, useEffect } from "react";
import  apiClient  from "../util/axios";
import { timeFormatter } from "../util/timeFormatter";

export const useTimeSlots = (selectedDate, dateCache) => {
  const [timeSlots, setTimeSlots] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTimeSlots = async () => {
      if (dateCache.current[selectedDate]) {
        setTimeSlots(dateCache.current[selectedDate]);
        setLoading(false);
        return;
      }

      try {
        setLoading(true);
        const response = await apiClient.get(`/timeslot`, {
          params: {
            date: timeFormatter(selectedDate).toISOString().split("T")[0],
          },
        });
        const data = await response.data;
        dateCache.current[selectedDate] = data; // 캐시에 저장
        setTimeSlots(data || []);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    if (selectedDate) {
      fetchTimeSlots();
    }
  }, [selectedDate]);

  return { timeSlots, loading, error };
};
