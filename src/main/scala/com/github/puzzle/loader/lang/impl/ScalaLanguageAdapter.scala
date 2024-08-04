package com.github.puzzle.loader.lang.impl

/*
 * Copyright 2016, 2018, 2019 FabricMC
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
import com.github.puzzle.loader.lang.LanguageAdapter
import com.github.puzzle.loader.mod.info.ModInfo

class ScalaLanguageAdapter extends LanguageAdapter{

  override def create[T](modInfo: ModInfo, s: String, aClass: Class[T]): T = {
    try {
      val objectClass = Class.forName(s + "$");
      val moduleField = objectClass.getField("MODULE$");
      val instance = moduleField.get(null).asInstanceOf[T];
      if (instance == null) throw new NullPointerException;
      return instance; // FUCK NOT HAVING RETURN

    }catch {
      case _: Exception =>
        println(s"Unable to find ${aClass.getName}$$MODULE$$");
        return aClass.getConstructor().newInstance();// FUCK NOT HAVING RETURN
    }

  }
}
