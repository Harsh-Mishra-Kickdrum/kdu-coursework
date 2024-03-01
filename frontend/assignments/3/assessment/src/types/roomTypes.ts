//    "roomTypes": [
//       {
//         "id": 1,
//         "name": "Single Room",
//         "costPerNight": "5000",
//         "currency": "INR",
//         "addOns": [
//           {
//             "name": "Breakfast",
//             "cost": "300",
//             "currency": "INR"
//           },
//           {
//             "name": "Extra Bed",
//             "cost": "2000",
//             "currency": "INR"
//           }
//         ]
//       },


export interface RoomType {
  id: number;
  name: string;
  costPerNight: string;
  currency: string;
  addOns: Array<{
    name: string;
    cost: string;
    currency: string;
  }>;
}

