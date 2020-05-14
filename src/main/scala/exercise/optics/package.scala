package exercise

/*
 * Copyright 2018-2019 Martin Tupý
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import util.alg.RomanNumber
import org.scalacheck.Gen

package object optics {


  abstract class Hall(num: Int, sectors: List[Sector])


  case class Sector(id: RomanNumber, shelfs: Map[String, Row])

  case class Row(number: Int, items: List[Item])

  case class Item(id: Int, name: String)

  val shelf: List[Row] = List(
    Row(1, List(
      Item(1031, "collar"),
      Item(1036, "chance"),
      Item(1117, "cheese"),
      Item(1187, "perforate"),
    )),
    Row(2, List(
      Item(1188, "welcome"),
      Item(1196, "winner"),
    )),
    Row(4, List(
      Item(1301, "highway"),
      Item(1409, "path"),
      Item(1440, "meeting"),
      Item(1503, "brother"),
      Item(1550, "undress"),
      Item(1566, "minute"),
      Item(1627, "sour"),
      Item(1640, "banquet"),
      Item(1683, "orgy"),
      Item(1905, "astonishing"),
    )),
    Row(3, List(
      Item(1910, "nervous"),
      Item(1924, "young"),
      Item(1978, "understanding"),
      Item(1983, "pay"),
    ))
  )

  val wordGen = Gen.oneOf(List("slow","athlete","cancer","mature","rib","driver","harmony","root","process","cucumber","leaflet","forum","profession","throne","colon","venture","week","marble","closed","diplomatic","gossip","pony","damage","clay","economy","computer virus","form","analysis","isolation","jurisdiction","planet","borrow","repeat","pluck","equip","cheque","absorb","autonomy","fixture","fat","injection","fare","pat","dollar","beer","gold","wrap","dough","express","finish","crevice","crime","quit","straw","premium","outlook","shelter","instal","agriculture","copper","bishop","pitch","imagine","need","winter","seem","shrink","communist","young","mold","reality","march","interest","deposit","damn","qualify","relate","cooperative","achieve","mixture","scenario","crowd","mirror","gasp","beef","tract","wash","paralyzed","socialist","elbow","base","embarrassment","triangle","shine","biology","lick","mosquito","drug","summit","sheet","momentum","impact","barrel","plant","dominate","glare","wear out","retain","coffee","price","promotion","outfit","copyright","size","ideal","sock","listen","handicap","asylum","direct","freeze","salesperson","accessible","wood","worm","compliance","cathedral","forge","bark","stain","contrast","fast","herd","horseshoe","ticket","delay","whisper","ant","biscuit","yearn","blonde","bar","imperial","chimpanzee","maximum","residence","cook","spend","diamond","tight","percent","terrace","winner","pole","ministry","trouser","manner","era","shell","motorist","native","stream","enthusiasm","illness","discreet","mystery","single","follow","crack","cassette","tongue","sweat","communication","landowner","colleague","pride","mix","mushroom","seize","connection","convert","film","smoke","volume","good","climate","provincial","elapse","tip","ostracize","innocent","die","self","straighten","scandal","interrupt","bulletin","snow","offer","duck","steel","teacher","heart","command","wolf","echo","cruel","green","appendix","suburb","reduce","statement","scene","please","weakness","aluminium","lion","influence","argument","tile","name","alcohol","cultivate","routine","fair","ignite","ball","scratch","restaurant","applied","retiree","horizon","imposter","miserable","depart","plastic","steam","allowance","firefighter","reason","orchestra","relinquish","pound","pour","champion","truck","reluctance","decrease","reptile","reverse","promise","suppress","reserve","mother","cooperate","breakfast","implication","risk","minority","simplicity","take","exchange","hero","suggest","hunter","style","relevance","spy","transport","remunerate","exploration","thrust","soul","Venus","precede","remember","button","embark","brake","map","practical","taxi","insight","warrant","gaffe","eyebrow","ribbon","prefer","fall","responsible","disclose","instrument","clothes","threshold","overview","innovation","pottery","directory","concentration","reporter","adviser","folk music","disturbance","prison","privilege","quiet","definition","delete","technology","sacred","helpless","put","bacon","rotten","text","disgrace","heel","evoke","nun","judgment","hardship","reflect","examination","prince","integrity","fix","weight","symbol","deviation","growth","marine","race","limit","counter","occupy","aware","cane","invisible","core","beneficiary","sum","AIDS","shock","case","helicopter","smile","ignore","fly","convulsion","duke","waiter","shallow","blue","game","industry","articulate","deck","bake","paragraph","skip","breeze","access","share","rent","lunch","use","priority","project","credit card","archive","air","major","live","absence","prosper","soil","inquiry","similar","elephant","distort","deputy","professor","undermine","belief","approach","organ","uncertainty","railroad","miss","amuse","deteriorate","way","photography","pit","table","artificial","relationship","return","stitch","lawyer","save","title","possible","solo","courage","humor","nut","ecstasy","village","date","environmental","match","orientation","north","incredible","screw","lake","carbon","sweep","misplace","keep","licence","system","genetic","coal","tumour","dictate","role","sheep","threat","constitution","ride","bloody","symptom","abortion","standard","score","prosecution","bean","wrist","bronze","baby","increase","astonishing","registration","unique","ghostwriter","virgin","freight","inspiration","tube","snake","safety","slip","regulation","digital","relation","voucher","equinox","storm","punish","tycoon","Sunday","modernize","notebook","ballot","endorse","predator","lemon","file","poison","wedding","headline","inflation","bat","modest","possibility","magazine","admit","brand","distinct","oven","drag","exercise","improve","sniff","qualified","blame","quaint","classify","include","road","evening","convince","staff","wagon","layer","sail","serious","respectable"))
  val idGen = Gen.choose(1000, 2000)

  val genItem: Gen[Item] = for {
    id <- idGen
    name <- wordGen
  } yield Item(id, name)

}
