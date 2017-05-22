#open "graphics";;
open_graph "";;
type direction = N | S | E | W;;

                     (* +--------------------+
                        |  Chemin aléatoire  |
                        +--------------------+ *)

(* chemin comportant n fois N, s fois S, e fois E et w fois W *)
let rec random_chemin (n,s,e,w) =
  let t = n+s+e+w in
  if t = 0 then []
  else begin
    let x = random__int(t) in
    if      x < n     then N :: random_chemin(n-1,s,e,w)
    else if x-n < s   then S :: random_chemin(n,s-1,e,w)
    else if x-n-s < e then E :: random_chemin(n,s,e-1,w)
    else                   W :: random_chemin(n,s,e,w-1)
  end
;;

                         (* +-------------+
                            |  Affichage  |
                            +-------------+ *)

let pas = 20;;
let rayon1 = 5;;
let rayon2 = 3;;

let dessine points =

  (* origine = centre de la fenêtre *)
  let x0 = size_x()/2 and y0 = size_y()/2 in
  moveto x0 y0;
  fill_circle x0 y0 rayon1;

  (* tracé des segments et des points *)
  let rec trace = function
    | []           -> ()
    | (x,y)::suite ->
	let r = if suite = [] then rayon1 else rayon2 in
	fill_circle (x0 + x*pas) (y0 + y*pas) r;
        lineto (x0 + x*pas) (y0 + y*pas);
        trace(suite)
  in trace(points)
;;

(* noircit un rectangle de diagonale [(x1,y1),(x2,y2)] *)
let noircit (x1,y1) (x2,y2) =
  let x0 = size_x()/2       and y0 = size_y()/2       in
  let x3 = min x1 x2        and y3 = min y1 y2        in
  let dx = (max x1 x2) - x3 and dy = (max y1 y2) - y3 in
  fill_rect (x0 + x3*pas) (y0 + y3*pas) (dx*pas) (dy*pas)
;;

                   (* +------------------------+
                      |  Conversion en points  |
                      +------------------------+ *)

let avance dir (x,y) = match dir with
  | N -> (x,      y + 1)
  | S -> (x,      y - 1)
  | E -> (x + 1,  y    )
  | W -> (x - 1,  y    )
;;

let rec points z chemin = match chemin with
  | [] -> [z]
  | dir::suite -> z :: points (avance dir z) suite
;;

  

                    (* +-----------------------+
                       |  Elimine les boucles  |
                       +-----------------------+ *)


(* liste d'association (point, prédecesseur) *)
let rec prédecesseurs points = match points with
  | u::v::suite -> (v,u) :: prédecesseurs (v::suite)
  | _ -> []
;;

(* élimine les boucles *)
let sans_boucle points =
  let z0 = hd(points)              in
  let pred = prédecesseurs(points) in
  let l = ref([hd(rev points)])    in
  while hd(!l) <> z0 do l := (assoc (hd !l) pred) :: !l done;
  !l
;;

let rec random_boucle(n) =
  let ch = random_chemin (n,n,n,n-1)    in
  let pt = sans_boucle(points (0,0) ch) in
  if list_length(pt) >= n then pt @ [(0,0)] else random_boucle(n)
;;


                        (* +---------------+
                           |  Remplissage  |
                           +---------------+ *)

let rec extrêmes points = match points with
  | []      -> failwith "liste vide"
  | [(x,y)] -> (y,y)
  | (x,y)::suite -> let (y1,y2) = extrêmes(suite) in (min y1 y, max y2 y)
;;

let rec intersecte y0 points = match points with
  | (x1,y1)::(x2,y2)::suite ->
      if min y1 y2 = y0 && max y1 y2 > y0
      then x1 :: intersecte y0 ((x2,y2)::suite)
      else intersecte y0 ((x2,y2)::suite)
  | _ -> []
;;

let remplit points =
  let (ymin,ymax) = extrêmes(points) in
  for y = ymin to ymax do
    let l = ref(sort__sort (prefix <=) (intersecte y points)) in
    while !l <> [] do match !l with
      | x1::x2::suite -> noircit (x1,y) (x2,y+1); l := suite
      | _ -> failwith "cas impossible"
    done
  done
;;

let test(pt) =
  clear_graph();
  set_color red;
  remplit pt;
  set_color blue;
  dessine pt
;;


                          (* +----------+
                             |  Dragon  |
                             +----------+ *)

let rec rotation(n) =
  if n mod 2 = 0 then n mod 4 = 0 else  rotation(n/2)
;;

let tourne sens dir = match dir with
  | N -> if sens then E else W
  | S -> if sens then W else E
  | E -> if sens then S else N
  | W -> if sens then N else S
;;

let rec dragon dir a b =
  if a = b then [] else dir :: dragon (tourne (rotation a) dir) (a+1) b
;;

let dessine_dragon pas n =

  clear_graph();
  let x0 = size_x()/2 and y0 = size_y()/2 in
  moveto x0 y0;

  let chemin = dragon N 0 (1 lsl n) in
  let pt = points (0,0) chemin in

  let rec trace = function
    | []           -> ()
    | (x,y)::suite -> lineto (x0 + x*pas) (y0 + y*pas); trace(suite)
  in trace(pt)
;;

dessine_dragon 5 11;;
